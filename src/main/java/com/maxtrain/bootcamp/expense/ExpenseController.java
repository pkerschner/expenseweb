package com.maxtrain.bootcamp.expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {
	
	@Autowired
	private ExpenseRepository expRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Expense>> getExpenses() {
		var expenses = expRepo.findAll();
		return new ResponseEntity<Iterable<Expense>>(expenses, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Expense> getExpense(@PathVariable int id) {
		var expense = expRepo.findById(id);
		if(expense.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Expense>(expense.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Expense> postExpense(@RequestBody Expense expense) {
		if(expense == null || expense.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var exp = expRepo.save(expense);
		return new ResponseEntity<Expense>(exp, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putExpense(@PathVariable int id, @RequestBody Expense expense) {
		if(expense == null || expense.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var exp = expRepo.findById(expense.getId());
		if(exp.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		expRepo.save(expense);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteExpense(@PathVariable int id) {
		var expense = expRepo.findById(id);
		if(expense.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		expRepo.delete(expense.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
