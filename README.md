# CS5004; Image Manipulation & Enhancement
* Linda Quach | quach.l@northeastern.edu
___
# HW 8;
## The requirements for HW08 include;
* Load/save an image from an ASCII PPM file.
  * The PPM format is a simple, text-based file format to store images. Basically contains a dump of the red, green, and blue values of each pixel, row-wise. Starter code was provided to read a PPM file, which we are allowed to use and manipulate.
* Create images that visualize the value, intensity, or luma of an image as defined in the assignment.
* Brighten or darken an image.
* Allow a user to interact with the program to use these operations, using simple text-based scripting.
* The program will handle text command input errors suitably.


### Program Script Commands;
* List of example test commands the program supports:
  1. `load image-path image-name`:
     * load an image from the specified path and refer it to henceforth in the program by the given image name.
  2. `save image-path image-name`: 
     * save the image with the given name to the specified path which should include the name of the file.
  3. `red-component image-name dest-image-name`:
     * Create a greyscale image with the red component of the image with the given name, and refer to it henceforth in the program by the given destination name. 
     * Similar commands for green, blue, value, luma, intensity components supported; substitute keyword for `red` in the example.
  4. `brighten increment image-name dest-image-name`:
     * brighten the image by the given increment to create a new image, referred to henceforth by the given destination name. The increment may be positive (brightening) or negative (darkening). 


## High-level design;
This program utilizes the MVC design pattern. Below is a description of each component and its purpose in the program.

### Model;
Within the model component, there are 3 packages: pixel, image, & transform. These packages and their respective classes work with the ImageModel class to manipulate the image.

* **MODEL PACKAGE**
  * ImageModel (interface): This interface represents the model of the program. It has methods to add an image to the model, and to get the image from the model.
    * `void addImage(String id, ImageState image);`, `ImageState getImage(String id);`
  * ImageModelImpl (class): This class implements the ImageModel interface. It represents the model of the program.
* **PIXEL PACKAGE**
  * Pixel (interface): This interface represents a pixel in an image, and extends PixelState interface. It has methods to set the red, green, and blue values of the pixel.
    * `void setR(int R)`, `void setG(int g);`, `void setB(int b);`, `void setRGB(int[] rgb);`
  * PixelState (interface): This interface represents the state of a pixel. It has methods to get the red, green, and blue values of the pixel. By separating the pixel state from the pixel, the view can access the pixel state without being able to change the pixel state.
    * `int getR();`, `int getG();`, `int getB();`, `int[] getRGB();`, `double getAlpha();`
  * PixelImpl (class): This class implements the Pixel interface. It represents a pixel in an image. 
* **IMAGE PACKAGE**
  * Image (interface): This interface represents an image. It has methods to set the pixels inside an image.
    * `void setPixel(int x, int y, int r, int g, int b);`
  * ImageState (interface): This interface represents the state of an image. It has methods to get the pixels inside an image. By separating the image state from the image, the view can access the image state without being able to change the image state.
    * `int getWidth();`, `int getHeight();`, `int getRedChannel(int x, int y);`, `int getGreenChannel(int x, int y);`, `int getBlueChannel(int x, int y);`
  * ImageImpl (class): This class implements the Image interface. It represents an image.
* **TRANSFORM PACKAGE**
  * This package includes all the transformation classes, such as `BrightenTransformation`, `GreyscaleRedTransformation`, `GreyscaleGreenTransformation`, `GreyscaleBlueTransformation`, `GreyscaleIntensityTransformation`, `GreyscaleLumaTransformation`, and `GreyscaleValueTransformation`.
  * It also includes the abstract class `Clamp` that contains the clamp method, which is used by all the transformation classes to clamp the RGB values of the pixels.

### Controller;
Withing the controller component, there are 4 packages: controller, io, & commands. These packages and their respective classes work with the ImageModel class to manipulate the image as the user commands.

* **CONTROLLER PACKAGE**
  * Controller (interface): This interface represents the controller of the program. It has methods to run the controller.
    * `void runController();`
  * ControllerImpl (class): This class implements the Controller interface. It allows for the user to interact with the program to use the operations, using simple text-based scripting. 
* **IO PACKAGE**
  * ImageLoader (interface): This interface represents the image loader of the program. It contains methods to load from a file and to save it as an ImageState.
    * `ImageState run();`
  * ImageSaver (interface): This interface represents the image saver of the program. It contains methods to save an ImageState to a file.
    * `void run();`
  * PPMImageLoader (class): This class implements the ImageLoader interface. It loads an image from a file and returns it as an ImageState.
  * PPMImageSaver (class): This class implements the ImageSaver interface. It saves an ImageState to a file.
* **COMMANDS PACKAGE**
  * Command (interface): This interface represents a command. It has methods to run the command.
    * `void run(Scanner scanner, ImageModel model);`
  * LoadPPMCommand (class): This class implements the Command interface. It loads an image from a file and adds it to the model.
  * SavePPMCommand (class): This class implements the Command interface. It saves an image to a file.
  * BrightenCommand (class): This class implements the Command interface. It brightens an image by the given increment.
  * ValueComponentCommand (class): This class implements the Command interface. It creates a greyscale image with the value, luma, intensity, RGB component of the image.

### View;
Withing the view component, there is 1 package: view. This package and its respective classes work with the ImageModel class to display the image to the user.

* **VIEW PACKAGE**
  * As of this version of the program, there is no GUI or view component. The view is the console. The view is responsible for displaying the image to the user, and for displaying error messages to the user.
  * An appendable is used to display the image , and to display error messages for testing.

___
## How to run the program;
* To run the program, run the `Main` class in the `src` folder.
* Using the console, the user can load an image, save an image, brighten an image, and create a greyscale image with the value, luma, intensity, RGB component of the image.
  * Please refer to the program script commands above for more information on commands.

___
## Image Sources & Citations;
* Image Source: Kirlin, P. (2019). Four by Four [Image]. Retrieved from https://www.cs.rhodes.edu/~kirlinp/courses/cs1/f19/projects/proj8/fourbyfour.ppm

The following images were created by me using Procreate, 
and scaled down to small pixel sizes. I am authorizing its use for this assignment.
* Image Source: Linda, Q. (2023). Pigeon [Image]. 
* Image Source: Linda, Q. (2023). BigPigeon [Image].
* Image Source: Linda, Q. (2023). BiggestPigeon [Image].
* Image Source: Linda, Q. (2023). Anya [Image].

___

## Thanks for reading! :)
































