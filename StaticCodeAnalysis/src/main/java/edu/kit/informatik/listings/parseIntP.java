public boolean assignValue(String number){
	try{
		int value = Integer.parseInt(number);
	}
	catch(NumberFormatException e){
		return false;
	}
	/* ... */
}
