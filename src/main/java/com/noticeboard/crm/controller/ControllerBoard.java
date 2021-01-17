package com.noticeboard.crm.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.noticeboard.crm.model.Board;
import com.noticeboard.crm.repository.RepositoryBoard;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "*")
public class ControllerBoard {

	@Autowired
	private RepositoryBoard repositoryBoard;
	
	@GetMapping("/list")
	public ResponseEntity<List<Board>> listBoard(){
		List<Board> list = repositoryBoard.findAll();
		return new ResponseEntity(list,HttpStatus.OK);
	}
	
	@GetMapping("list/{id}")
	public Optional<Board> findNotice(@PathVariable(value="id") int id){
	   return repositoryBoard.findById(id);			
	}
	
	@PostMapping("/create")
	public String addNotice(@RequestBody Board b) {			
		if(b.getTitle() != null) {
			 repositoryBoard.save(b);
			 return "News successfully registered number " + b.getId();
		}else {
			throw new RuntimeException("Could not create warnings");
		}	   
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteBoard(@PathVariable(value="id") int id) {
		Optional<Board> board = repositoryBoard.findById(id);
	    if(board.isPresent()) {	    	
	    	repositoryBoard.delete(board.get());
	    	return "Notice is deleted with id - " + id;
	    }else {              
	    	throw new RuntimeException("Notice not found for the id - " + id);
	    }
		
	}
	
	@PutMapping("/update")
	public String updateBoard(@RequestBody Board b) {
		Optional<Board> board = repositoryBoard.findById(b.getId());
		 if(board.isPresent()) {	    	
		    	repositoryBoard.save(b);
		    	return "News updated successfully - " + b.getId();
		    }else {              
		    	throw new RuntimeException("Could not update - " +  b.getId());
		    }   
	}
	
	
}
