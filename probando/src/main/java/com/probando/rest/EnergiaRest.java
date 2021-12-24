package com.probando.rest;




import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.probando.dao.EnergyDao;
import com.probando.entity.Energy;







@RestController
@RequestMapping("energy")
public class EnergiaRest {
	
	@Autowired
	private EnergyDao energyDao;
	
	@GetMapping
	public ResponseEntity<List<Energy>> getEnergia(){
		List<Energy> energia =  energyDao.findAll();
	return ResponseEntity.ok(energia);			
					        
	}
	
	@RequestMapping(value="{id}")
	public ResponseEntity<Energy> getEnergiaById(@PathVariable("id") Long id){
		Optional<Energy>optionalEnergy = energyDao.findById(id);
		if(optionalEnergy.isPresent()) {
			return ResponseEntity.ok(optionalEnergy.get());
		}else {
			
			return ResponseEntity.noContent().build();
		}
					        
	}
	
	@PostMapping
	public ResponseEntity<Energy> createEnergy(@RequestBody Energy energy){
	 Energy newEnergy = energyDao.save(energy);
	 return ResponseEntity.ok(newEnergy);
					        
	}
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<Void> deleteEnergy(@PathVariable("id") Long id){
	 energyDao.deleteById(id);
	 return ResponseEntity.ok(null);
					        
	}
	
	@PutMapping
	public ResponseEntity<Energy> updateEnergy(@RequestBody Energy energy){
		Optional<Energy>optionalEnergy = energyDao.findById(energy.getId());
		if(optionalEnergy.isPresent()) {
			Energy updateEnergy = optionalEnergy.get();
			updateEnergy.setNombre(energy.getNombre());
			energyDao.save(updateEnergy);
			return ResponseEntity.ok(updateEnergy);
		}else {
			
			return ResponseEntity.noContent().build();
		}
					        
	}
	
	/*@GetMapping
	@RequestMapping(value = "hola", method=RequestMethod.GET)
	public String hola() {
		return "Hola";
	}
*/
}
