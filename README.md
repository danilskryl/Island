<h1>Final project of the second module</h1>
<p>This is a <em>simulation</em> of an island with wild animals.
There are only three species: predators, herbivores and omnivores. 
The animal must move around the field in search of food, otherwise it will <b>die</b>.</p>
<h2>Animal Interface</h2>
<p>The <b>Animal</b> interface implements the <b>Organism</b> interface and adds additional functionality specific to animals in the simulation.</p>
<p>The <b>Animal</b> interface also implements the <b>Cloneable</b> interface, allowing animals to be cloned on the Island.</p>
<h3>An Animal has the following attributes:</h3>

<b>x</b>: an integer representing the x-coordinate of the animal's location on the Island.

<b>y</b>: an integer representing the y-coordinate of the animal's location on the Island.

<b>sex</b>: a boolean representing the animal's sex.

<b>weight</b>: a double representing the animal's weight.

<b>kgToFedUp</b>: a double representing the amount of food needed to make the animal full.

<b>speed</b>: a double representing the animal's speed.

<b>maxCountOnCell</b>: an integer representing the maximum number of animals of the same type that can be on the same cell.

<b>fullness</b>: a double representing the animal's current level of fullness.

<b>animalsEaten</b>: a map representing the types of animals that the animal can eat and the percentage chance of successfully catching and eating them.

<b>timeToReproduct</b>: an integer representing the number of time units it takes for the animal to reproduce.

<b>island</b>: an instance of the Island class representing the Island on which the animal lives.

<b>task</b>: an instance of the ScheduledFuture class representing the scheduled task associated with the animal's thread.

<h3>An Animal has the following methods:</h3>
<b>move()</b>: updates the animal's location on the Island based on its speed and a random direction.

<b>reproduct()</b>: creates a new instance of the animal on the Island if certain conditions are met.

<b>eat()</b>: attempts to eat another animal on the same cell.

<b>animalsOnCellWhichCanEat(Animal animal)</b>: returns a list of animals on the same cell that can be eaten by the given animal.

<b>die()</b>: removes the animal from the Island and cancels its scheduled task.

<b>reduction()</b>: reduces the animal's fullness level based on its speed and weight.

<b>boundsCheck()</b>: ensures the animal's location on the Island stays within the boundaries of the Island.

<b>run()</b>: implements the Runnable interface and defines the behavior of the animal's thread.

<h3>Used technologies:</h3>
<a href="https://junit.org/junit5/">JUnit 5</a>, <a href="https://slf4j.org">SLF4J</a>, <a href="https://projectlombok.org">Lombok</a>