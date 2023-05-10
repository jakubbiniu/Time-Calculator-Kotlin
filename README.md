
# Time-Calculator-Kotlin
### Description
The app written in Kotlin has two main functions. It can calculate the sum and difference between hours and the difference in days between two dates.
### How to do calculations on hours?
Just input two hours in the given places and press the "+" or "-" button to calculate the sum or difference between the two hours. The result will be displayed on the first spot, the second will be 0.
You can input only positive numbers but the result may be both positive and negative. 
If you insert more than 60 minutes or seconds it will not be a problem, the calculator can convert it into full hours/minutes.
### How to do calculations on dates?
There are two ways to use that function in the app. You can choose dates on two date pickers and the difference in days will be shown below. 
Another way is to enter the date on the first date picker and press the "+" button. 
Then the second picker will display the date which is the number of days you chose after the date in the first picker. You can insert negative numbers to go back in time.
Another feature in my app is the function that counts the number of working days between two days and displays the number of days. 
It takes into consideration every holiday in Poland like Easter Monday, Corpus Christi (for these two I used Meeus/Jones/Butcher algorithm), 1st of January, 1st and 3rd of May, 15th of August,
1st and 11th of November and 25th and 26th of December.
