students.csv: Contains information about the Student, is used for when the Student class is instantiated.
This includes the course they are doing and personal information displayed on the transcript.
Student users have the ability to change this information.

grades.csv: Contains information about the student's grades. Is used for when StudentGrades is instantiated.
There is a line for each student. "NA" means a result has not yet been assigned.

Take this line for example:
92546743,LM110,2020/21,0,76,99,45,60,22,,LM110,2020/21,1,77,NA,43,87,NA,,AQ822,2022/23,0,76,99,45,60,22

The first token is the Student ID, Followed by the course code and the semester that the following grades are for.
So this student has grades of 76, 99, 45, 60, and 22 for the first semester of LM110 (Bachelors for Game Development).
The college year is showed after the college code.
The semesters are separated by an empty delimiter.
The student then has 77, NA, 43, 87 and NA for the 2nd semester.
This student has yet to receive their grades for their 2nd and 5th module, so they are marked as NA.
This student dropped out, and then came back and decided to do a different course, with a course code of AQ822.
Faculty users have the ability to write this data to the grades.csv file.

course.csv: Contains information about a course a student is attending, is used for gathering information
from an instantiated Programme class. This included the list of modules associated with the programme,
if the course is a research programme or not and basic information like course director.

Take this line for example:
LM121,Computer Science Common Entry,4,Dr Emil Vassev,emil.i.vassev@ul.ie,CS4012CS4141CS4221ET4011MA4111,CS4043CS4222CS4182ET4162MA4402
The 1st token is the course code.
The 2nd is the name.
The 3rd is the length of the course in years (4 years in this case).
The 4th is the course director.
The following tokens are the modules, in order, for each semester.
In this case there are 2 semesters, so there are 2 tokens with the module codes.
Each module code is 6 characters in length.

modules.csv: Contains the grade to mark scheme for modules. Every module must include
"NG", "F", "D2", "D1", "C3", "C2", "C1", "B3", "B2", "B1", "A2" and "A1" grades.
The 1st token after the module code represents the credits of the module.
The numbers after correspond to each grade respectively.
Dissertations and thesis also fall under this category.