package com.guiji.auth.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guiji.auth.util.PermissionResolve;
import com.guiji.auth.vo.MenuTree;
import com.guiji.user.dao.SysMenuMapper;
import com.guiji.user.dao.entity.SysMenu;

@Service
public class MenuService {

	@Autowired
	private SysMenuMapper mapper;
	
	@Autowired
	private PermissionResolve resolve;

	public void insert(SysMenu menu){
		menu.setCreateTime(new Date());
		menu.setUpdateTime(new Date());
		mapper.insertSelective(menu);
		resolve.clean();
	}

	public void delete(Long id){
		mapper.deleteByPrimaryKey(id);
		resolve.clean();
	}

	public void update(SysMenu menu){
		mapper.updateByPrimaryKeySelective(menu);
		resolve.clean();
	}

	public SysMenu getMenuById(Long id){
		return mapper.selectByPrimaryKey(id);
	}
	
	public Map<String,Object> getAllMenus(Long roleId){
		Map<String,Object> map=new HashMap<String,Object>();
		List<SysMenu> allMenu=mapper.getAllMenus();
		map.put("menus", parseTree(allMenu));
		
		List<Long> selected=mapper.getSelectedMenuId(roleId);
		map.put("selected", selected);
		return map;
	}
	
	public List<MenuTree> getMenus(Long userId){
		List<SysMenu> allMenu=mapper.getMenuByUserId(userId);
		return parseTree(allMenu);
	}
	
	public Map<String,String> getAllPermissions(){
		List<Map<String,String>> permList=mapper.getAllPermissions();
		Map<String,String> result=new HashMap<>();
		permList.forEach((item)->{
			result.put(item.get("url"), item.get("permission"));
		});
		
		return result;
	}
	
	private List<MenuTree> parseTree(List<SysMenu> allMenu){
		Map<Long,MenuTree> map=new HashMap<>();
		List<MenuTree> list=new ArrayList<>();
		allMenu.stream().forEach((item)->{
			Long pid=item.getPid();
			MenuTree node=new MenuTree();
			node.setParent(item);
			if(0==pid){
				list.add(node);
				map.put(item.getId(), node);
			}else{
				MenuTree parent=map.get(pid);
				if(parent!=null){
					parent.getChild().add(node);
					map.put(item.getId(), node);
				}
			}
		});
		return list;
	}

}
