package com.maxtrain.bootcamp.expenseline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/expenselines")
public class ExpenselineController {
	
	@Autowired
	private ExpenselineRepository explnRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Expenseline>> getExpenselines() {
		var expenselines = explnRepo.findAll();
		return new ResponseEntity<Iterable<Expenseline>>(expenselines, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Expenseline> getExpensline(@PathVariable int id) {
		var expenseline = explnRepo.findById(id);
		if(expenseline.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Expenseline>(expenseline.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Expenseline> postExpenseline(@RequestBody Expenseline expenseline) {
		if(expenseline == null || expenseline.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var expln = explnRepo.save(expenseline);
		return new ResponseEntity<Expenseline>(expln, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putExpenseline(@PathVariable int id, @RequestBody Expenseline expenseline) {
		if(expenseline == null || expenseline.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var expln = explnRepo.findById(expenseline.getId());
		if(expln.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		explnRepo.save(expenseline);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteExpenseline(@PathVariable int id) {
		var expenseline = explnRepo.findById(id);
		if(expenseline.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		explnRepo.delete(expenseline.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
