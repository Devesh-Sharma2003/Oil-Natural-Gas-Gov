package com.ongc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ongc.Repo.MenuMstRepo;
import com.ongc.Repo.RoleMenuMappingRepo;
import com.ongc.Repo.RoleMstRepo;
import com.ongc.Repo.UserMstRepo;
import com.ongc.Repo.UserRoleRepo;
import com.ongc.dto.responseDto.MenuMstModelDto;
import com.ongc.dto.responseDto.RoleDetailsDto;
import com.ongc.dto.responseDto.UserDetailsDto;
import com.ongc.exceptions.BadRequestException;
import com.ongc.mappper.UserRoleDetailsMapper;
import com.ongc.model.MenuMstModel;
import com.ongc.model.RoleMstModel;
import com.ongc.model.UserMstModel;
import com.ongc.model.UserRoleMappingModel;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserMstRepo userRepo;

	@Autowired
	private UserRoleDetailsMapper userRoleDetailsMapper;

	@Autowired
	private UserRoleRepo userRoleRepo;

	@Autowired
	private RoleMstRepo roleMstRepo;

	@Autowired
	private RoleMenuMappingRepo roleMenuMappingRepo;
	
	@Autowired
	private MenuMstRepo menuMstRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserMstModel userdata = userRepo.findByUserName(username.toLowerCase());

		if (userdata == null) {
			throw new UsernameNotFoundException("user not found with username: " + username);
		}
		return new User(userdata.getUsername(), userdata.getPassword(), new ArrayList<>());
	}

	public Map<String, Object> getLoginUserData(String username) {
		Map<String, Object> response = new HashMap<>();
		UserMstModel userData = userRepo.findByUserName(username);

//		write code to have menu and subMenu list according to the user login in reposne...

		response.this.getMenuAndSubMenuList(userData.getId());

		if (userData != null) {
			UserDetailsDto dto1 = userRoleDetailsMapper.userToDto(userData);
			response.put("user", dto1);

			Long roleId = userRoleRepo.findByUserId(userData.getId());

			RoleDetailsDto roleDetailsDto = new RoleDetailsDto();

			Optional<RoleMstModel> roleDetails = this.roleMstRepo.findById(roleId);

			roleDetailsDto.setRoleId(roleDetails.get().getId());
			roleDetailsDto.setRoleName(roleDetails.get().getRole());

			List<RoleDetailsDto> roleList = new ArrayList<>();
			roleList.add(roleDetailsDto);
			response.put("roleMap", roleList);
		}

		return response;
	}

	public UserMstModel getUserDetailsByUserName(String username) {
		return userRepo.findByUserName(username);
	}

	public ResponseEntity<?> validateTokenUsername() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			if (authentication != null && authentication.isAuthenticated()) {
				return new ResponseEntity<>("validation successfully", HttpStatus.OK);
			} else
				throw new SecurityException("Unauthorized Access!");
		} catch (Exception e) {
			return new ResponseEntity<>("validation failed", HttpStatus.BAD_REQUEST);
		}
	}

	public Map<String, Object> getMenuAndSubMenuList(Long userId){
		
		Map<String, Object> response = new HashMap<>();

		List<UserRoleMappingModel> userRoleMappingModelList = userRoleRepo.findByUserId_Id(userId);

		if (userRoleMappingModelList != null && !userRoleMappingModelList.isEmpty()) { 
			for (UserRoleMappingModel roleld : userRoleMappingModelList) {
				List<Long> menuIdList = roleMenuMappingRepo.findAllMenuIdByRoleId(roleld.getRoleId().getId());
				System.out.println(menuIdList);
				if (!menuIdList.isEmpty()) {

					List<MenuMstModelDto> parentMenuMstDtoList = new ArrayList<>();
					List<MenuMstModelDto> subMenuMstDtoList = new ArrayList<>();
					System.out.println(menuIdList);
					
					 for (Long i : menuIdList) {
								System.out.println("hello "+i);
								Optional<MenuMstModel> parentMenuMstObj = menuMstRepo.findByParentMenuConditions(i);

								if(parentMenuMstObj.isPresent()) {
									MenuMstModelDto menuMstModelDto = new MenuMstModelDto();
									menuMstModelDto.setMenuId(parentMenuMstObj.get().getMenuId());
									menuMstModelDto.setMenuName(parentMenuMstObj.get().getMenuName());
									menuMstModelDto.setRoleld(roleId.getRoleld().getId());
									menuMstModelDto.setLink (parentMenuMstObj.get().getLink()); 
									parentMenuMstDtoList.add(menuMstModelDto);
									System.out.println(parentMenuMstDtoList);
								}
								
								List<MenuMstModel> subMenuMstObj = menuMstRepo.findBySubMenuConditions(i);
								List<MenuMstModelDto> subMenuList = subMenuMstObj.stream().map((val) -> {
										MenuMstModelDto menuMstModelDto = new MenuMstModelDto(); 
										menuMstModelDto.setMenuId(val.getMenuId());
										menuMstModelDto.setMenuName(val.getMenuName());
										menuMstModelDto.setRoleld(roleld.getRoleld().getId());
										menuMstModelDto.setLink(val.getLink());
										menuMstModelDto.setParentMenuId(val.getParentMenuId());
										return menuMstModelDto; 
										}).collect(Collectors.toList());
										subMenuMstDtoList.addAll(subMenuList);
										System.out.println(subMenuMstObj);
					}
			System.out.println(parentMenuMstDtoList);
					 			System.out.println(subMenuMstDtoList);
					 			response.put("parentMenuList", parentMenuMstDtoList); 
					 			response.put("subMenuList", subMenuMstDtoList);		
						}else {
							throw new BadRequestException("No Such menu id's exist!!");
						}
					}
				}else {
					throw new BadRequestException();
				}
			return response;	
		}
}
