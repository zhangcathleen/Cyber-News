
Our animation model consists of a list of Instructions which are given to it by the user.




An Instruction is one of a CreateInstruction and an OperateInstruction.


A CreateInstruction specifies a new object (IShape) in our animation. It requires an instructionID
which is an identifier used to track specific Instructions, a String name for the IShape, and a
ShapeType(which is an enum) specifying the type of shape.

An OperateInstruction specifies an operation to be performed on an IShape. It requires an
instructionID, the name of the shape(which is unique), two ints representing the start and end of
the time period to preform the operation over, two ShapeStates representing the starting and ending
state of the shape (a ShapeState includes the position, dimensions, and color), and an Operation
(one of MOVE, SCALE, COLOR) representing the type of operation which is being preformed.




An IShape is one of an Ellipse, Star or a Rectangle. We have an AbstractShape class to abstract
commonalities between all shapes, which include methods to move a shape to a specific position,
scale the shapes dimensions by a factor, set the color of the shape, and getters which return
specific fields of the shapes. Every IShape has all of the fields, as well as a ShapeType, an Enum
representing the type of the shape.

IShape extends the IViewOnlyShape interface, which only has getter methods. We did this so that we
can supply a list of non-mutable shapes to the controller, which should only be able to get
information about them.


The AnimationModel interface represents the model of our animation. This stores information about
all of the objects in the animation and what operations are preformed on them at different times.

Our AnimationModel interface has only three methods. These are:

addInstruction(Instruction instruction), which adds either a CreateInstruction or an
OperateInstruction to our model. This method throw an exception if you give it a CreateInstruction
for a shape with the same name as an existing shape, if you give it an OperateInstruction for a
shape which does not exist, or if you give it an OperateShape for a shape which already has an
OperateShape with the same type of Operation on an overlapping time interval.

removeInstruction(String instructionID), which removes an Instruction from our AnimationModel with
an ID matching the given ID. Throws an exception if no Instruction in the model.

getAnimationState(int tick), which return a list of IShape representing the state of the
AnimationModel at a certain time, by calculating each shapes state based on the current list of
Instructions. *** We were told we would be given a method implementing this, so we did not
implement it ourselves, but we left it in as something we know we will need ***



Our program also includes a Controller interface and its implementation. When supplied a view and a
model the controller will supply the view with information about the model and request that
information from the model, not letting them interact directly.

The only method in our controller is beginAnimation, which starts the interaction between the view
and model. For visual view it starts a timer and calls on the view to render the model at certain
times. For text based views it has the view render the text statically.



There are three vies in our program. These are the TextAnimationView, SVGAnimationView, and the
VisualAnimationView. Each view has one method in it called "render" which displays what is passed in
corresponding to their individual views.

The text based view utilizes the getDescription method in our model (indirectly through the
controller of course) and sends it to System.out it in its render method.

The SVG based view also utilizes the getDescription method but modifies the text to be consistent
with SVG standards, sending the output to System.out.

The visual view utilizes JPanel and JFrame to draw the animation for the user. It uses te list of
objects to have each one render itself on the screen.



