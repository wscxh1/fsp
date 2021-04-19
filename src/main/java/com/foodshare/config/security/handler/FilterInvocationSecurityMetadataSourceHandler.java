package com.foodshare.config.security.handler;

import com.foodshare.entity.GroupPermission;
import com.foodshare.entity.UserPermission;
import com.foodshare.entity.UserPermissionExample;
import com.foodshare.mapper.GroupPermissionMapper;
import com.foodshare.mapper.UserPermissionMapper;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class FilterInvocationSecurityMetadataSourceHandler implements FilterInvocationSecurityMetadataSource {

    @Resource
    private GroupPermissionMapper permissionMapper;

    private Map<String, Collection<ConfigAttribute>> map =null;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if(map ==null) loadResourceDefine();
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        Set<String> items = map.keySet();
        for (String s : items) {
            resUrl = s;
            matcher = new AntPathRequestMatcher(resUrl);
            if (matcher.matches(request)) {
                System.out.println(resUrl);
                return map.get(resUrl);
            }
        }
        return null;
    }


    /**
     * 加载权限表中所有权限
     */
    public void loadResourceDefine(){
        map = new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        List<GroupPermission> permissions = permissionMapper.getAll();
        for(GroupPermission permission : permissions) {
            System.out.println(permission.toString());
            array = new ArrayList<>();
            cfg = new SecurityConfig(String.valueOf(permission.getGroupId()));
            //此处只添加了用户的名字，其实还可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。此处添加的信息将会作为MyAccessDecisionManager类的decide的第三个参数。
            array.add(cfg);
            //用权限的getUrl() 作为map的key，用ConfigAttribute的集合作为 value，
            map.put(permission.getPermissionUrl(), array);
        }

    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}