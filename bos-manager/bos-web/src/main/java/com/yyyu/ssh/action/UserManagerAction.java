package com.yyyu.ssh.action;

import com.yyyu.ssh.TextUtils;
import com.yyyu.ssh.dao.bean.UserDataTablesReturn;
import com.yyyu.ssh.dao.bean.UserReturn;
import com.yyyu.ssh.domain.SysUser;
import com.yyyu.ssh.service.inter.IUserService;
import com.yyyu.ssh.templete.BaseAction;
import com.yyyu.ssh.utils.TypeConversion;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：用户管理Action
 *
 * @author yu
 * @date 2017/10/23.
 */
@Controller
@Scope("prototype")
@Namespace("/userManager")
public class UserManagerAction extends BaseAction<SysUser> {

    @Autowired
    private IUserService userService;

    private String[] columns = new String[]{"id", "username", "salary", "telephone", "gender", "remark"};
    private String orderName;
    private String orderDid;

    @Action(value = "getUserByPage")
    public void getUserByPage() {
        try {

            //draw的次数，原样返回
            String draw = getRequestParam("draw");
            //开始行
            String start = getRequestParam("start");
            //分页长度
            String length = getRequestParam("length");
            //需要排序列的索引
            String orderColumn = getRequestParam("order[0][column]");
            //过滤条件
            String searchValue = getRequestParam("search[value]");

            UserDataTablesReturn userDataTablesReturn = new UserDataTablesReturn();
            Integer usersTotal = userService.getUsersTotal();
            int startInt = TypeConversion.str2Int(start);
            int lengthInt = TypeConversion.str2Int(length, 10);
            DetachedCriteria criteria = userService.getCriteria();
            if (!TextUtils.isEmpty(searchValue)) {//搜索
                criteria.add(Restrictions.or(
                        Restrictions.like("telephone", searchValue + "%"),
                        Restrictions.like("username", searchValue + "%")
                ));
            }
            if (!TextUtils.isEmpty(orderColumn)) {//排序(不为空时赋值，为空用原始排序规则)
                //需要排序的列名
                orderName = columns[TypeConversion.str2Int(orderColumn, 0)];
                //排序方式 asc des
                orderDid = getRequestParam("order[0][dir]");
            }
            if ("asc".equalsIgnoreCase(orderDid)) {
                criteria.addOrder(Order.asc(orderName));
            } else if ("desc".equalsIgnoreCase(orderDid)) {
                criteria.addOrder(Order.desc(orderName));
            }
            List<SysUser> userList = userService.getUserByPage(criteria, startInt, lengthInt);
            if (draw != null && !"".equals(draw)) {
                userDataTablesReturn.setDraw(Integer.parseInt(draw));
            }
            userDataTablesReturn.setRecordsTotal(usersTotal);
            userDataTablesReturn.setRecordsFiltered(usersTotal);
            List<UserReturn> userReturnList = new ArrayList<>();
            for (SysUser user : userList) {
                UserReturn userReturn = new UserReturn();
                userReturn.setId(user.getUserId());
                userReturn.setUsername(user.getUsername());
                userReturn.setSalary(user.getSalary());
                userReturn.setBirthday(user.getBirthday());
                userReturn.setGender(user.getGender());
                userReturn.setRemark(user.getRemark());
                userReturn.setStation(user.getStation());
                userReturn.setTelephone(user.getTel());
                userReturnList.add(userReturn);
            }
            userDataTablesReturn.setData(userReturnList);
            printJson(userDataTablesReturn, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
