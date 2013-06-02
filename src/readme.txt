243 OOP: Project 02 README
===============================
To create an initial .bnk file run the jar with 2 arguments
first being the name of the .bnk file you would like (ex. test.bnk)
secondly the batch file to initialize accounts in the .bnk a sample
batch file would look like this:

o x 9111 9111 500
o c 9222 9222 500
o s 9333 9333 500
o c 9444 9444 10
d 9333 100
w 9111 100
d 9444 500
a
c 9111
c 9222
c 9333
c 9444

where the first argument is the command type and the following arguments
are parameters for that command.

Commands:
o - Open Account Parameters (Account Type, Account ID, Account Pin, Starting Balance)
d - Deposit Parameters (Account ID, Amount to deposit)
w - Withdrawl Parameters (Account ID, Amount to withdrawl)
a - Apply Interest
c - Close Account Parameters (Account ID)

Account Types:
x - Checking Account
c - CD Account
s - Savings Account

Then to run the application with the GUI after you have created
a .bnk file you just run the jar with the argument of the .bnk
file itself.

0. Author Information
---------------------

CS Username: 	txs6138		Name:  		Tyler Schoen

1. Problem Analysis
---------

Summarize the analysis work you did. 
What new information did it reveal?  How did this help you?
How much time did you spend on problem analysis?

	I sketched out how I thought the project was going to work and how
	everything was going to interect. This gave me a feel for how the
	project was going to flow together and how many classes and files
	I was going to need to complete the project. This helped me save
	time by not doing things as I was going and having to figure them
	out as I went. I spent about an hour on problem analysis.

2. Design
---------

Explain the design you developed to use and why. What are the major 
components? What connects to what? How much time did you spend on design?
Make sure to clearly show how your design uses the MVC model by
separating the UI "view" code from the back-end game "model".

	The design is probably what saved me the most time, once I had all
	of my classes and files laid out it was a matter of getting them
	to interact with each other and writing the code. The bank model 
	connects to the bank GUI, the ATM model, and the batch object. then
	the ATM model connects to the ATM GUI. I spent about 1 to 2 hours
	on my design layout for the project and then an additional 4 to 5
	hours on my GUI design. I made sure that the only code involved with
	my GUI's were the action listeners for the buttons which then passed 
	the input to the ATM or Bank model appropriately.

3. Implementation and Testing
-------------------

Describe your implementation efforts here; this is the coding and testing.

What did you put into code first?
How did you test it?
How well does the solution work? 
Does it completely solve the problem?
Is anything missing?
How could you do it differently?
How could you improve it?

How much total time would you estimate you worked on the project? 
If you had to do this again, what would you do differently?

	I put the Bank model into code first and applied the serialization
	and saving/creating/opening file process into the main to give me 
	a base to start testing it off of. I then made the Account Abstract
	Class and the rest of the classes for the accounts and necessary 
	methods to implement them into the bank. I tested this by creating 
	new .bnk files and having a method run before save that added accounts
	to the bank. The solution worked very smoothly and I moved onto the GUI
	soon after that. It didn't completely solve the problem because I didn't
	have all the components necessary to interact with it so I left it alone
	at that point in time and moved onto the next component of the project.
	I could do this differently by writing the GUI and Bank model simultaneously
	however I felt like it was a better option to complete the bank model 
	first to make sure that the model was completely seperate from the GUI.

4. Development Process
-----------------------------

Describe your development process here; this is the overall process.

How much problem analysis did you do before your initial coding effort?
How much design work and thought did you do before your initial coding effort?
How many times did you have to go back to assess the problem?

What did you learn about software development?

	I took probably about an hour or two to do my initial analysis and then
	I laid out all the files in eclipse that I would need and began to code
	the more repetitive files like the Account Abstract Class and the Account
	classes themselves and then began coding the Bank model and the main method
	simultaneously until they were functional. I had to go back probably about 3
	times to assess the problem, for the most part I knew exactly what I had to
	do and how everything connected. It was just the fact of getting everything 
	to function the way it was supposed to. 
	
