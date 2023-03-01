package com.local.naruto.knowledge.util;

import com.local.naruto.knowledge.entity.UserModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class StreamOperationUtil {

    private List<UserModel> userList1() {
        UserModel user1 = new UserModel();
        user1.setUserId("12345");
        user1.setUserName("naruto01");
        user1.setPassword("password01");
        user1.setRealName("realName01");

        UserModel user2 = new UserModel();
        user2.setUserId("123456");
        user2.setUserName("naruto02");
        user2.setPassword("password02");
        user2.setRealName("realName02");

        UserModel user3 = new UserModel();
        user3.setUserId("123457");
        user3.setUserName("naruto03");
        user3.setPassword("password03");
        user3.setRealName("realName03");

        return Arrays.asList(user1, user2, user3);
    }

    private List<UserModel> userList2() {
        UserModel user1 = new UserModel();
        user1.setUserId("12345");
        user1.setUserName("naruto01");
        user1.setPassword("password01");
        user1.setRealName("realName01");

        UserModel user2 = new UserModel();
        user2.setUserId("12345");
        user2.setUserName("naruto01");
        user2.setPassword("password02");
        user2.setRealName("realName02");

        UserModel user3 = new UserModel();
        user3.setUserId("123457");
        user3.setUserName("naruto03");
        user3.setPassword("password03");
        user3.setRealName("realName03");

        return Arrays.asList(user1, user2, user3);
    }

    // 根据实体列表中的若干个属性去重
    public List<UserModel> filterByDuplicateValueInOneList() {
        List<UserModel> userList = userList1();
        return userList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(
                    Comparator.comparing(user -> user.getUserId() + "-" + user.getUserName()))),
            ArrayList::new));
    }

    // 比较两个list中多个相同字段过滤
    public List<UserModel> filterByDuplicateValueInTwoList() {
        List<UserModel> dbUser = userList1();
        List<UserModel> inputUser = userList2();
        // 如果userId和userName值相同，则将其过滤
        return inputUser.stream().filter(e -> {
            for (UserModel s : dbUser) {
                if (e.getUserId().equals(s.getUserId())
                    && e.getUserName().equals(s.getUserName())) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());
    }

    // 求对象列表的并集
    public List<UserModel> getObjectUnionList() {
        List<UserModel> userA = userList1();
        List<UserModel> userB = userList2();
        userA.addAll(userB);
        return userA.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(
                    () -> new TreeSet<>(
                        Comparator.comparing(UserModel::getUserId)
                    )
                ), ArrayList::new
            )).stream().sorted(Comparator.comparing(UserModel::getUserName))
            .collect(Collectors.toList());
    }

    // 求对象列表交集
    public List<UserModel> getObjectInterSectionList() {
        List<UserModel> userA = userList1();
        List<UserModel> userB = userList2();
        return userA.stream().filter(
            userInfo -> userB.stream().map(UserModel::getUserName).collect(Collectors.toList())
                .contains(userInfo.getUserName())).collect(
            Collectors.toList());
    }

    // 求对象列表差集
    public List<UserModel> getObjectDifferenceList(){
        List<UserModel> userA = userList1();
        List<UserModel> userB = userList2();
        return userA.stream().filter(
            userInfo -> !userB.stream().map(UserModel::getUserName).collect(Collectors.toList())
                .contains(userInfo.getUserName())).collect(
            Collectors.toList());
    }

    // 根据多属性求差集
    public List<UserModel> getDifferenceByMany() {
        List<UserModel> userA = userList1();
        List<UserModel> userB = userList2();
        return userA.stream().filter(
            // 将集合转为以userId和userName为key的map
            user -> !userB.stream().collect(
                    Collectors.toMap(params -> params.getUserId() + "-" + params.getUserName(),
                        value -> value))
                // 判断key是否存在
                .containsKey(user.getUserId() + "-" + user.getUserName())
        ).collect(Collectors.toList());
    }

    // 求两个集合并集
    public List<String> getUnionList() {
        List<String> listA = Arrays.asList("1", "2", "3");
        List<String> listB = Arrays.asList("3", "4", "5");
        listA.addAll(listB);
        return listA.stream().distinct().collect(Collectors.toList());
    }

    // 求两个集合交集
    public List<String> getInterSectionList() {
        List<String> listA = Arrays.asList("1", "2", "3");
        List<String> listB = Arrays.asList("3", "4", "5");
        return listA.stream().filter(listB::contains).collect(Collectors.toList());
    }

    // 求两个集合差集
    public List<String> getDifferenceList() {
        List<String> listA = Arrays.asList("1", "2", "3");
        List<String> listB = Arrays.asList("3", "4", "5");
        return listA.stream().filter(s -> !listB.contains(s)).collect(Collectors.toList());
    }
}
