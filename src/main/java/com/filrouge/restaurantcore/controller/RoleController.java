package com.filrouge.restaurantcore.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.filrouge.restaurantcore.dto.RoleDto;
import com.filrouge.restaurantcore.dto.UserDto;
import com.filrouge.restaurantcore.service.IRoleService;





/**
 * REST role management services.
 * 
 * @author Hermann
 *
 */
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/role/*")
public class RoleController {

	@Autowired
	IRoleService roleService;
	

	@GetMapping(value = "/roles")
	public ResponseEntity<Collection<RoleDto>> findAll() {
		Collection<RoleDto> roles = roleService.findAll();
		return new ResponseEntity<Collection<RoleDto>>(roles, HttpStatus.OK);
		
		//TODO findByName
	}

	@PostMapping(value = "/create")
	@Transactional
	public ResponseEntity<RoleDto> save(@RequestBody RoleDto roleDto) {

		RoleDto roleCreated = roleService.save(roleDto);
		return new ResponseEntity<RoleDto>(roleCreated, HttpStatus.CREATED);
	}
	
	@GetMapping("/{name}")
    public ResponseEntity<List<RoleDto>> findByName(@PathVariable("name") String name) {
         List<RoleDto> roles = roleService.findByName(name);
        return new ResponseEntity<List<RoleDto>>(roles, HttpStatus.OK);
    }
	
	 @DeleteMapping("/delete/{id}")
	 public void deleteRoleById(@PathVariable("id") String id){
	        this.roleService.deleteRoleById(id);
	    }
	 
	 /**
		 * Mise à jour du role
		 * @param id l'identifiant du Role
		 * @param roleDto les données à mettre à jour
		 * @return le role mis à jour
		 */
		@PutMapping("/update/{id}")
		@Transactional
		public ResponseEntity<RoleDto> update(@PathVariable String id,
				@RequestBody RoleDto roleDto) {

			roleDto.setId(id);
			RoleDto roleUpdate = roleService.update(roleDto);
			return new ResponseEntity<RoleDto>(roleUpdate, HttpStatus.CREATED);
		}

	
	
}
