package org.um.feri.ears.tuning;

import java.util.Random;

public class ControlParameter {

	public String name;
	public String type;
	public double suggested_value;
	public double lower_bound;
	public double upper_bound;
	public double epsilon_neighbourhood;
	public double factor;
	public double precision;
	
	public ControlParameter(String aName, String aType, double aSuggested_value, double aLower_bound, double aUpper_bound, double aEpsilon_neighbourhood, double aPrecision) {
		name = aName;
		type = aType;
		suggested_value = aSuggested_value;
		lower_bound = aLower_bound;
		upper_bound = aUpper_bound;
		epsilon_neighbourhood = aEpsilon_neighbourhood;
		precision = aPrecision;
		Random rand = new Random();
		factor = (upper_bound-lower_bound)/(3.9+rand.nextDouble()); // for initialisation to the interval [1,5]
	}
	
	public ControlParameter(String aName, String aType, double aLower_bound, double aUpper_bound, double aPrecision) {
		name = aName;
		type = aType;
		suggested_value = 0;
		lower_bound = aLower_bound;
		upper_bound = aUpper_bound;
		epsilon_neighbourhood = 0;
		precision = aPrecision;
		Random rand = new Random();
		factor = (upper_bound-lower_bound)/(3.9+rand.nextDouble()); // for initialisation to the interval [1,5]
	}
	
	public double correctValue(double aValue){
		double value = Math.round(aValue/precision) * precision;	
		if (value<lower_bound) value = lower_bound;
		if (value>upper_bound) value = upper_bound;
		return value;
	}
	public double randomNeighbour(double aValue){
		double neighbour = aValue;
		Random r = new Random();
		double start = aValue-epsilon_neighbourhood;
		double end = aValue+epsilon_neighbourhood;
		if (type.compareTo("int")==0){
			if (start<lower_bound){
				start = lower_bound;
				neighbour = (int)(start) + r.nextInt((int)(end - start));
			}else if (end>upper_bound){
				end = upper_bound;
				neighbour = (int)(start) + r.nextInt((int)(end - start));
			}else{
				neighbour = (int)(start) + r.nextInt((int)(end - start));
			}
		}else{
			if (start<lower_bound){
				start = lower_bound;
				neighbour = (start) + (end - start)*r.nextDouble();
			}else if (end>upper_bound){
				end = upper_bound;
				neighbour = (start) + (end - start)*r.nextDouble();
			}else{
				neighbour = (start) + (end - start)*r.nextDouble();
			}
		}
		return neighbour;		
	}
	
	public double randomValue(){
		double value = 0;
		Random r = new Random();
		double start = lower_bound;
		double end = upper_bound;
		if (type.compareTo("int")==0){
			value = (int)(start) + r.nextInt((int)(end - start + 1));
			value = Math.round(value/precision) * (int)precision;
			if (value < lower_bound) value = lower_bound;
			if (value > upper_bound) value = upper_bound;
		}else{
			value = (start) + (end - start)*r.nextDouble();
			value = Math.round(value/precision) * precision;
			if (value < lower_bound) value = lower_bound;
			if (value > upper_bound) value = upper_bound;
		}
		return value;		
	}
	public double randomValue(double a, double b){
		double value = 0;
		Random r = new Random();
		double start = a;
		double end = b;
		if (start == end) return start;
		if (type.compareTo("int")==0){
			value = (int)(start) + r.nextInt((int)(end - start + 1));
			value = Math.round(value/precision) * (int)precision;
		}else{
			value = (start) + (end - start)*r.nextDouble();
			value = Math.round(value/precision) * precision;
		}
		return value;		
	}
}
