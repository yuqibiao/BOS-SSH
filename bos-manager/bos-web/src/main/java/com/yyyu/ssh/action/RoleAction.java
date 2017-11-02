package com.yyyu.ssh.action;

import com.yyyu.ssh.TextUtils;
import com.yyyu.ssh.dao.bean.RoleDataTablesReturn;
import com.yyyu.ssh.dao.bean.RoleReturn;
import com.yyyu.ssh.domain.SysRole;
import com.yyyu.ssh.service.inter.IRoleService;
import com.yyyu.ssh.templete.BaseAction;
import com.yyyu.ssh.utils.ResultUtils;
import com.yyyu.ssh.utils.TypeConversion;
import com.yyyu.ssh.utils.bean.BaseJsonResult;
import org.apache.struts2.convention.annotation.Action;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;


/**
 * 功能：role相关的Action
 *
 * @author yu
 * @date 2017/11/2.
 */
@Controller
@Scope("prototype")
@Action(value = "/role")
public class RoleAction extends BaseAction<SysRole> {

    @Autowired
    private IRoleService roleService;

    private String[] columns = new String[]{"roleId", "roleName", "description", "code", "available"};
    private String orderName;
    private String orderDid;

    @Action(value = "getRoleByPage")
    public void getRoleByPage() {

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

            RoleDataTablesReturn roleDataTablesReturn = new RoleDataTablesReturn();
            int startInt = TypeConversion.str2Int(start);
            int lengthInt = TypeConversion.str2Int(length, 10);
            Integer roleTotal = roleService.getRolesTotal();

            DetachedCriteria criteria = roleService.getCriteria();
            if (!TextUtils.isEmpty(searchValue)) {//搜索
                criteria.add(Restrictions.or(
                        Restrictions.like("roleName", searchValue + "%"),
                        Restrictions.like("description", searchValue + "%")
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
            List<SysRole> roleList = roleService.getRoleByPage(criteria, startInt, lengthInt);

            roleDataTablesReturn.setDraw(TypeConversion.str2Int(draw, 0));
            roleDataTablesReturn.setRecordsTotal(roleTotal);
            roleDataTablesReturn.setRecordsFiltered(roleTotal);
            List<RoleReturn> roleReturnList = new ArrayList<>();
            for (SysRole role : roleList) {
                RoleReturn roleReturn = new RoleReturn();
                roleReturn.setRoleId(role.getRoleId());
                roleReturn.setRoleName(role.getRoleName());
                roleReturn.setDescription(role.getDescription());
                roleReturn.setCode(role.getCode());
                roleReturn.setAvailable(role.getAvailable());
                roleReturnList.add(roleReturn);
            }
            roleDataTablesReturn.setData(roleReturnList);
            printJson(roleDataTablesReturn, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Action(value = "addRole")
    public void addRole() {
        BaseJsonResult result;
        try {
            roleService.addRole(getModel());
            result = ResultUtils.success();
        } catch (Exception e) {
            result = ResultUtils.error(500, e.getMessage());
            e.printStackTrace();
        }
        printJson(result, null);
    }

    @Action(value = "deleteRole")
    public void deleteRole() {
        BaseJsonResult result;
        try {
            roleService.deleteRole(getModel().getRoleId());
            result = ResultUtils.success("删除成功");
        } catch (Exception e) {
            result = ResultUtils.error(500, e.getMessage());
            e.printStackTrace();
        }
        printJson(result, null);
    }

    @Action("modifyRole")
    public void modifyRole() {
        Long roleId = getModel().getRoleId();
        String roleName = getModel().getRoleName();
        String description = getModel().getDescription();
        String code = getModel().getCode();
        Byte available = getModel().getAvailable();
        SysRole sysRole = roleService.getRoleById(roleId);
        if (!TextUtils.isEmpty(roleName)){
            sysRole.setRoleName(roleName);
        }
        if (!TextUtils.isEmpty(description)){
            sysRole.setDescription(description);
        }
        if (!TextUtils.isEmpty(code)){
            sysRole.setCode(code);
        }
        if (available!=null){
            sysRole.setAvailable(available);
        }
        BaseJsonResult result;
        try {
            roleService.modifyRole(sysRole);
            result = ResultUtils.success("修改成功");
        } catch (Exception e) {
            result = ResultUtils.error(500, e.getMessage());
            e.printStackTrace();
        }
        printJson(result, null);
    }

}
