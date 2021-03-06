Bullet direction change story

Narrative:
In order to verify the bullet movement when it strikes the rectangle sides
As a development team
I want to use Behaviour-Driven Development
					 
Scenario:  The bullet strikes left wall coming from direction 1
Given The direction of the bullet 1
When the bullet strikes left
Then the direction should change to 2

Scenario:  The bullet strikes bottom wall coming from direction 1
Given The direction of the bullet 1
When the bullet strikes bottom
Then the direction should change to 4

Scenario:  The bullet strikes bottom wall coming from direction 2
Given The direction of the bullet 2
When the bullet strikes bottom
Then the direction should change to 3

Scenario:  The bullet strikes right wall coming from direction 2
Given The direction of the bullet 2
When the bullet strikes right
Then the direction should change to 1

Scenario:  The bullet strikes right wall coming from direction 3
Given The direction of the bullet 3
When the bullet strikes right
Then the direction should change to 4

Scenario:  The bullet strikes top wall coming from direction 3
Given The direction of the bullet 3
When the bullet strikes top
Then the direction should change to 2

Scenario:  The bullet strikes top wall coming from direction 4
Given The direction of the bullet 4
When the bullet strikes top
Then the direction should change to 1

Scenario:  The bullet strikes left wall coming from direction 4
Given The direction of the bullet 4
When the bullet strikes left
Then the direction should change to 3