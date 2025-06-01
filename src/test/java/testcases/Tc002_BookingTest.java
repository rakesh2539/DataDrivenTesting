package testcases;

import org.testng.annotations.Test;

import base.BaseClass;

public class Tc002_BookingTest extends BaseClass {
	
	
	@Test
	public void bookingWithValidData() {
		
		System.out.println(" previous booking  is successfull");
		
		
		//System.out.println(" Booking is not successfull ");
	}
	
    @Test
    public void bookingWithInvalidData() {
    	
    	System.out.println("Booking was not done with valid data");
    }
}
