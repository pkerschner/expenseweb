package com.maxtrain.bootcamp.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/items")
public class ItemController {
	
	@Autowired
	private ItemRepository itemRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Item>> getItems() {
		var items = itemRepo.findAll();
		return new ResponseEntity<Iterable<Item>>(items, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Item> getItem(@PathVariable int id) {
		var item = itemRepo.findById(id);
		if(item.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Item>(item.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Item> postItem(@RequestBody Item item) {
		if(item == null || item.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var it = itemRepo.save(item);
		return new ResponseEntity<Item>(it, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putItem(@PathVariable int id, @RequestBody Item item) {
		if(item == null || item.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var it = itemRepo.findById(item.getId());
		if(it.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		itemRepo.save(item);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteEmployee(@PathVariable int id) {
		var item = itemRepo.findById(id);
		if(item.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		itemRepo.delete(item.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
