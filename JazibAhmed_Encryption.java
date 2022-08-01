public class JazibAhmed_Encryption //The encryption class 
{
    public static void main (String[] args)
    {

    } // main method
    
    public static boolean isALowerLetter (char key) //Letter checker that checks letters to see if they are lowercase
    {
	int keyint = (int) key; //Getting the ascii value of the letter
	
	//Checking ascii value to see if the letter comes under the ascii code of lowercase letters
	if (keyint >= 97 && keyint <= 122) 
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }


    public static boolean isACapLetter (char key) //Letter checker that checks letters to see if they are uppercase
    {
	int keyint = (int) key;  //Getting the ascii value of the letter
	
	//Checking ascii value to see if the letter comes under the ascii code of uppercase letters
	if (keyint >= 65 && keyint <= 90)
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }


    public static String Shifter (String input) //Shifter method that divides a word by half and puts the second half before the first half
    {
	//Declaring multiple variables
	char letter; 
	int num = 0, lastspace = 0;
	String output = "", word = "";
	
	input += " "; //Adding a space after the last word so last word can be shifted

	//For loop that runs through the whole input
	for (int i = 0 ; i < input.length () ; i++)
	{
	    letter = input.charAt (i); //Assigning every letter to variable "letter"

	    if (letter == ' ') //If statement that checks to see when a space occurs
	    {
		word = input.substring (lastspace, i); //The variable "word" takes in the word using the location of the last space and where the loop is at
		num = (int) word.length () / 2; //Dividing the word length by half
		word = word.substring (num, word.length ()) + word.substring (0, num) + " "; //Printing the second half before the first half
		lastspace = i + 1; //Changing the value of lastspace to match the current space before moving on
		output += word; //Adding the word to the "output variable"
	    }

	}
	return output; //Returning the output variable once the loop is done
    }


    public static String ReverseShifter (String input) //ReverseShifter method that reverses the Shifter method for decryption
    {
	//Declaring multiple variables
	char letter;
	int num = 0, lastspace = 0;
	String output = "", word = "";

	//For loop that runs through the input
	for (int i = 0 ; i < input.length () ; i++)
	{
	    letter = input.charAt (i);  //Assigning every letter to variable "letter"

	    if (letter == ' ') //If statement that checks to see when a space occurs
	    {
		word = input.substring (lastspace, i); //The variable "word" takes in the word using the location of the last space and where the loop is at
		num = (int) word.length () / 2; //Dividing the word length by half
		
		if (word.length () % 2 != 0 && word.length () > 1) //If statement to see if word length is even or odd
		{
		    num += 1; //Adding one so that the odd word can be reversed properly as values are rounded during shifting while encrypting
		    word = word.substring (num, word.length ()) + word.substring (0, num) + " "; //Printing original word
		    lastspace = i + 1; //Changing the value of lastspace to match the current space before moving on
		}
		else //Else statement for when word length is even
		{
		    word = word.substring (num, word.length ()) + word.substring (0, num) + " "; //Printing original word
		    lastspace = i + 1; //Changing the value of lastspace to match the current space before moving on
		}
	    }
	    else //Else statement to keep continuing if the current letter is not a space
	    {
		continue;
	    }
	    output += word; //Adding the word to the output
	}
	return output; //Returning the output variable once the loop is done
    }


    public static String encrypt (String input) //Encrypt method that encrypts the secret message
    {
	//Declaring multiple variables, including the encryption "key"
	int enc[] = {2, 4, 7, 3, 6, 3, 2, 8, 1, 12 , 10, 8}; //Encryption key
	String output = "";
	int num = 0, m = 0, n = 0;
	char letter;
	
	input = Shifter(input); //Calling on the shifter method to shift each word in the input

	//For loop that runs through the input
	for (int i = 0 ; i < input.length () ; i++)
	{
	    if (m == 8) //If statement that makes sure the encryption key loops over and over again and does not break
	    {
		m = 0;//Making the m value 0 when it reaches 8
	    }

	    letter = input.charAt (i); //Assigning every letter to variable "letter"

	    //If statement to see when the letter is a capital letter
	    if (isACapLetter (letter) == true)
	    {
		num = (int) letter; //Making the letter an ascii value and assigning the value to "num" variable
		
		n = (155 - num) + enc [m]; //Reversing the alphabet for each letter and shifting it the stated spaces by the encryption key 
		
		if (n > 90) //If statement for when a letter gets shifted too far
		{
		    //If letter goes too far, the letter is brought back to the beginning of alphabet and be shifted the remaining times
		    n = 64 + (((155 - num) + enc [m]) - 90); 
		}
		output += (char) n; //Outputting each encrypted letter one by one

	    }

	    //Else if statement to see when the letter is a lowercase letter
	    else if (isALowerLetter (letter) == true)
	    {
		num = (int) letter; //Making the letter an ascii value and assigning the value to "num" variable
		n = (219 - num) + enc [m]; //Reversing the alphabet for each letter and shifting it the stated spaces by the encryption key
		
		if (n > 122) //If statement for when a letter gets shifted too far
		{
		     //If letter goes too far, the letter is brought back to the beginning of alphabet and be shifted the remaining times
		    n = 96 + (((219 - num) + enc [m]) - 122); 
		}
		output += (char) n; //Outputting each encrypted letter one by one

	    }
	    
	    //Else statement to leave numbers, special characters and spaces out of the encryption
	    else
	    {
		output += letter; //Outputting the letter as it is
	    }
	    
	    m++; //Increasing the m value so the next value within the encryption key is chosen
	}

	return output; //Returning the output variable once loop is done
    }


    public static String decrypt (String Dinput) //Decrypt method that decrypts the secret message
    {
	//Declaring multiple variables, including the encryption/decryption "key"
	int dec[] = {2, 4, 7, 3, 6, 3, 2, 8, 1, 12, 10, 8}; //Encryption/Decryption key
	char letter;
	int num = 0, m = 0, n = 0;
	String output = "";

	//If statement to add a space to the encrypted lanaguage if there isnt one so that the last word can be reverse shifted properly
	if (Dinput.charAt (Dinput.length () - 1) != ' ')
	{
	    Dinput += " "; //Adding a space for last word to be reverse shifted properly
	}

	//For loop that runs through the input
	for (int i = 0 ; i < Dinput.length () ; i++)
	{
	    if (m == 8) //If statement that makes sure the encryption key loops over and over again and does not break
	    {
		m = 0; //Making the m value 0 when it reaches 8
	    }
	    
	    letter = Dinput.charAt (i); //Assigning every letter to variable "letter"

	    //If statement to see when the letter is a capital letter
	    if (isACapLetter (letter) == true)
	    {
		num = (int) letter; //Making the letter an ascii value and assigning the value to "num" variable
		n = num - dec [m]; //Subtracting the ascii value by the encryption key value it was encrypted with to reverse the encryption
		
		if (n < 65) //If statement to realize which letters got added too far in the encryption and needed to be sent to the beginning of alphabet
		{
		    n = (90 - (dec [m] - (num - 64))); //Going back to the end of the alphabet and using the decryption key to find original ascii value
		    n = 155 - n; //Reversing the alphabet again to get the original ascii value of letter
		    output += (char) n; //Outputting the original letter
		}
		else //Else statement for letters that had not been added too far while encrypting
		{
		    n = (155 - (num - dec [m])); //Subtracting the decryption value and reversing the alphabet to find the original value of the letter
		    output += (char) n; //Outputting the original letter
		}
	    }
	    
	    //Else if statement to see when the letter is a lowercase letter
	    else if (isALowerLetter (letter) == true)
	    {
		num = (int) letter; //Making the letter an ascii value and assigning the value to "num" variable
		n = num - dec [m]; //Subtracting the ascii value by the encryption key value it was encrypted with to reverse the encryption
		
		if (n < 97) //If statement to realize which letters got added too far in the encryption and needed to be sent to the beginning of alphabet
		{
		    n = (122 - (dec [m] - (num - 96))); //Going back to the end of the alphabet and using the decryption key to find original ascii value
		    n = 219 - n; //Reversing the alphabet again to get the original ascii value of letter
		    output += (char) n; //Outputting the original letter
		}
		else
		{
		    n = (219 - (num - dec [m])); //Subtracting the decryption value and reversing the alphabet to find the original value of the letter
		    output += (char) n; //Outputting the original letter
		}
	    }
	    
	    else //Else statement to leave numbers, special characters and spaces out of the encryption
	    {
		output += letter; //Outputting the letter as it is
	    }
	    m++; //Increasing the m value so the next value within the encryption key is chosen
	}

	output = ReverseShifter(output); //Calling on the ReverseShifter method to reverse shift the output to the original message before the output is returned
	
	return output; //Returning the output variable once loop is done
	
    }
}


