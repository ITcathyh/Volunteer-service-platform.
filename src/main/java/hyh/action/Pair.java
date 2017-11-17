package hyh.action;

import hyh.entity.AppEmail;
import hyh.entity.User;
import hyh.entity.UserInfo;
import hyh.global.Variable;
import hyh.service.UserInfoService;
import hyh.service.UserService;
import hyh.util.Email;
import org.apache.log4j.Logger;
import java.util.*;

public class Pair {
    private static Logger log = Logger.getLogger("hyh.action.Pair");

    synchronized public static int pair(UserService userservice,
                                           UserInfoService userinfoservice, int type) {
        LinkedList<User> users = new LinkedList<User>(userservice.getByTypeAndPairid(type, 0));

        if (users.size() < 2) {
            return -1;
        }

        Deque<User> stack = new ArrayDeque<User>(32);
        //Stack<User> stack = new Stack<User>();
        User user, match;
        UserInfo userinfo;
        int cot = 0;

        while (users.size() > 1) {
            user = users.get(0);
            userinfo = userinfoservice.getByStudentidAndType(user.getStudentid(), type);

            users.remove(user);

            if (userinfo != null) {
                match = makeDegree(user, userinfo, users, userinfoservice, type);

                if (match == null) {
                    stack.push(user);
                    continue;
                }

                if (update(user, match, match.getStudentid(), user.getStudentid(), userservice)) {
                    users.remove(match);
                    cot++;
                } else {
                    update(user, match, 0, 0, userservice);

                    return cot;
                }
            }
        }

        for (User temp : users) {
            stack.push(temp);
        }

        while (stack.size() > 1) {
            user = stack.pop();
            match = stack.pop();

            if (update(user, match, match.getStudentid(), user.getStudentid(), userservice)) {
                users.remove(match);
                cot++;
            } else {
                update(user, match, 0, 0, userservice);

                return cot;
            }
        }

        return cot;
    }

    synchronized public static int sendPairEmail(Email email, UserService userservice, int type) {
        String genre;

        if (type == 1) {
            genre = "早餐叫醒";
        } else if (type == 2) {
            genre = "相约自习";
        } else {
            return -1;
        }

        LinkedList<User> users = new LinkedList<User>(userservice.getPairedByType(type));

        if (type == 1) {
            List<User> temp = userservice.getByPairtype(3);
            int size = temp.size(), i = 0;
            User tuser, mathch;

            while (i < size) {
                tuser = temp.get(i++);

                if (tuser.getPairtype() == 3) {
                    mathch = userservice.getByStudentidAndType(tuser.getPairid(), 1);

                    if (mathch == null) {
                        users.add(tuser);
                    } else {
                        if (temp.remove(mathch)) {
                            size--;
                        }
                    }
                }
            }
        }

        if (users.size() < 2) {
            return -1;
        }

        User user, match;

        Deque<AppEmail> stacks[];
        //Stack<AppEmail> stacks[];
        int i = 0;
        int len = users.size();
        int size = len / 3 + 1;

        if (size < 2) {
            size = 3;
            stacks = new Deque[]{new ArrayDeque<AppEmail>(size)};
            //stacks = new Stack[]{new Stack<AppEmail>()};
        } else {
            stacks = new Deque[]{new ArrayDeque<AppEmail>(size), new ArrayDeque<AppEmail>(size),new ArrayDeque<AppEmail>(size)};
           // stacks = new Stack[]{new Stack<AppEmail>(), new Stack<AppEmail>(), new Stack<AppEmail>()};
        }

        while (users.size() > 1) {
            user = users.get(0);
            match = userservice.getByStudentidAndType(user.getPairid(), type);

            if (match == null) {
                users.remove(user);
                continue;
            }

            users.remove(user);
            users.remove(match);

            if (stacks[i].size() >= size) {
                email.sendEmail(stacks[i++], genre, userservice, 1);
            }

            stacks[i].push(new AppEmail(match.getEmail(), user.getName(), user.getQq(), user.getPhone(), user.getStudentid()));
            stacks[i].push(new AppEmail(user.getEmail(), match.getName(), match.getQq(), match.getPhone(), match.getStudentid()));
        }

        email.sendEmail(stacks[i], genre, userservice, 1);

        if (type == 1) {
            try {
                Thread.sleep(2000);

                while (!email.isOver()) {
                    Thread.sleep(400);
                }
            } catch (Exception e) {
                return -1;
            }
        }

        return 1;
    }

    private static User makeDegree(User user, UserInfo userinfo, List<User> users, UserInfoService userinfoservice, int type) {
        User match = null, temp;
        UserInfo tempinfor;
        int result, max = 0;
        ListIterator<User> it = users.listIterator();

        while (it.hasNext()){
            temp = it.next();
            result = 0;
            tempinfor = userinfoservice.getByStudentidAndType(temp.getStudentid(), type);

            if (tempinfor != null) {
                if (userinfo.getSex() == 0 && tempinfor.getSex() == 0 && user.getSelfsex() == temp.getSelfsex()) {
                    result += 3;
                } else if (userinfo.getSex() == 1 && tempinfor.getSex() == 1 && user.getSelfsex() != temp.getSelfsex()) {
                    result += 3;
                }

                if (userinfo.getTime().equals(tempinfor.getTime())) {
                    result += 3;
                }

                if (userinfo.getCollege().equals(tempinfor.getSelfcollege())) {
                    result += 2;
                } else if (userinfo.getSelfcollege().equals(tempinfor.getSelfcollege())) {
                    result += 2;
                }

                if (user.getStudentid() / 10000 == temp.getStudentid() / 10000) {
                    result += 1;
                }

                if (max < result) {
                    max = result;
                    match = temp;
                }
            } else {
                Variable.errornum++;
                log.error("User(id" + temp.getStudentid() + ")'s infomation has gone");
                it.remove();
            }
        }

        return match;
    }

    private static boolean update(User user, User match, int pairid1, int pairid2, UserService userservice) {
        try {
            user.setPairid(pairid1);
            match.setPairid(pairid2);

            return userservice.updatePairid(pairid1, user.getId()) == 1 &&
                    userservice.updatePairid(pairid2, match.getId()) == 1;
        } catch (Exception e) {
            Variable.errornum++;
            log.error("update user error\n" + e);
            return false;
        }
    }
}
