package com.yyyu.ssh.action;

import com.yyyu.ssh.TextUtils;
import com.yyyu.ssh.dao.bean.PermissionDataTablesReturn;
import com.yyyu.ssh.dao.bean.PermissionReturn;
import com.yyyu.ssh.domain.SysPermissions;
import com.yyyu.ssh.service.inter.IPermissionsService;
import com.yyyu.ssh.templete.BaseAction;
import com.yyyu.ssh.utils.ResultUtils;
import com.yyyu.ssh.utils.TypeConversion;
import com.yyyu.ssh.utils.bean.BaseJsonResult;
import org.apache.struts2.convention.annotation.Action;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：Permission相关操作对应的Action
 *
 * @author yu
 * @date 2017/11/2.
 */

@Controller
@Scope("prototype")
@Action("/permission")
public class PermissionAction extends BaseAction<SysPermissions>{

    @Autowired
    private IPermissionsService permissionsService;

    private String[] columns = new String[]{"perId", "perPid", "name", "description", "code", "page","type","available"};
    private String orderName;
    private String orderDid;


    @Action("listPermissionByPage")
    public void listPermissionByPage(){

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

            PermissionDataTablesReturn dataTablesReturn = new PermissionDataTablesReturn();
            Integer permissionTotal =permissionsService.getPermissionTotal();
            int startInt = TypeConversion.str2Int(start);
            int lengthInt = TypeConversion.str2Int(length, 10);
            DetachedCriteria criteria = permissionsService.getCriteria();
            if (!TextUtils.isEmpty(searchValue)) {//搜索
                criteria.add(Restrictions.or(
                        Restrictions.like("name", searchValue + "%"),
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
            List<SysPermissions> permissionsList = permissionsService.getPermissionByPage(criteria , startInt , lengthInt);
            dataTablesReturn.setDraw(TypeConversion.str2Int(draw , 0));
            dataTablesReturn.setRecordsFiltered(permissionTotal);
            dataTablesReturn.setRecordsFiltered(permissionTotal);
            List<PermissionReturn> permissionReturnList = new ArrayList<>();
            for (SysPermissions permission:permissionsList) {
                PermissionReturn permissionReturn = new PermissionReturn();
                permissionReturn.setPerId(permission.getPerId());
                permissionReturn.setPerPid(permission.getPerPid());
                permissionReturn.setName(permission.getName());
                permissionReturn.setDescription(permission.getDescription());
                permissionReturn.setCode(permission.getCode());
                permissionReturn.setPage(permission.getPage());
                permissionReturn.setType(permission.getType());
                permissionReturn.setAvailable(permission.getAvailable());
                permissionReturnList.add(permissionReturn);
            }
            dataTablesReturn.setData(permissionReturnList);
            printJson(dataTablesReturn , null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Action("addPermission")
    public void addPermission(){
        String name = getModel().getName();
        BaseJsonResult result;
        try {
            if (TextUtils.isEmpty(name)){
                result= ResultUtils.error(501, "权限名不能为空");
            }else{
                permissionsService.addPermission(getModel());
                result = ResultUtils.success("添加权限成功");
            }
        } catch (Exception e) {
            result = ResultUtils.error(500 , e.getMessage());
            e.printStackTrace();
        }
        printJson(result , null);
    }

    @Action("deletePermission")
    public void deletePermission(){
        Long perId = getModel().getPerId();
        BaseJsonResult result;
        try {
            if (perId==null||perId==0){
                result= ResultUtils.error(501, "perId为空");
            }else{
                permissionsService.deletePermission(getModel());
                result = ResultUtils.success("删除权限成功");
            }
        } catch (Exception e) {
            result = ResultUtils.error(500 , e.getMessage());
            e.printStackTrace();
        }
        printJson(result , null);
    }

    @Action("modifyPermission")
    public void modifyPermission(){

        long perId = getModel().getPerId();
        Long perPid = getModel().getPerPid();
        String name = getModel().getName();
        String description = getModel().getDescription();
        String code = getModel().getCode();
        String page = getModel().getPage();
        Short type = getModel().getType();
        Byte available = getModel().getAvailable();
        SysPermissions permission = permissionsService.getPermissionById(perId);
        if (perPid!=null){
            permission.setPerPid(perPid);
        }
        if (TextUtils.isEmpty(name)){
            permission.setName(name);
        }
        if (TextUtils.isEmpty(description)){
            permission.setDescription(description);
        }
        if (TextUtils.isEmpty(code)){
            permission.setCode(code);
        }
        if (TextUtils.isEmpty(page)){
            permission.setPage(page);
        }
        if (type!=null){
            permission.setType(type);
        }
        if (available!=null){
            permission.setAvailable(available);
        }
        BaseJsonResult result;
        try {
            permissionsService.modifyPermission(permission);
            result = ResultUtils.success("修改成功");
        } catch (Exception e) {
            result = ResultUtils.error(500 , e.getMessage());
            e.printStackTrace();
        }
        printJson(result , null);
    }


}
