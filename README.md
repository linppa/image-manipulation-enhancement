# CS5004; Image Manipulation & Enhancement Program

* Linda Quach | quach.l@northeastern.edu

___

This program is a basic image manipulation and enhancement tool designed to allow the
user to load an image from a file, and to apply transformations to the image.
Such transformations include brightening, darkening, greyscaling, and applying
filters to the image, as well as viewing a histogram of the image's RGB values.

## Table of Contents;

* [How to run the program](#how-to-run-the-program)
* [Program Script Commands](#program-script-commands)
* [Graphical User Interface (GUI) Instructions](#graphical-user-interface-gui-instructions)
* [Change log](#change-log)
* [High-level design](#high-level-design)
* [Image Sources & Citations](#image-sources--citations)

___

## How to run the program;

There are three ways to run the program:

* Command-line arguments, Interactive text mode, and Graphical user interface
  (GUI) mode.

    * Using the command-line arguments, this program accepts the following inputs:
        * `-file path-of-script-file`: The script file must be in the same directory
          as the program file and must be a `.txt` file. The script file must
          contain commands that are supported by the program.

        * `-text`: when invoked in this manner the program should open in an
          interactive text mode, allowing the user to type the script and execute it
          one line at a time.

        * ` `: when invoked in this manner the program should open in an interactive
          GUI mode, allowing the user to load an image from a file and visually see
          the image. The GUI contains buttons to allow the user to manipulate the
          image, such as brightening, darkening, greyscaling, and applying filters
          to the image. The user may also view a histogram of the image's RGB
          values.

* Please refer to the program script commands below for more information on
  commands.

___

## Program Script Commands;

This is a list of example commands the program supports for the script file or interactive text
mode:

1. `load image-path image-name`:
    * load an image from the specified path and refer it to henceforth in the
      program by the given image name.

2. `save image-path image-name`:
    * save the image with the given name to the specified path which should
      include the name of the file.

3. `red-component image-name dest-image-name`:
    * Create a greyscale image with the red component of the image with the
      given name, and refer to it henceforth in the program by the given
      destination name.
    * Similar commands for `green, blue, value, luma, intensity` components
      supported; substitute keyword for `red` in the example.
        * `green-component image-name dest-image-name`
        * `blue-component image-name dest-image-name`
        * `value-component image-name dest-image-name`
        * `luma-component image-name dest-image-name`
        * `intensity-component image-name dest-image-name`

4. `brighten increment image-name dest-image-name`:
    * brighten the image by the given increment to create a new image, referred
      to henceforth by the given destination name. The increment may be
      positive (brightening) or negative (darkening).

5. `blur image-name dest-image-name`:
    * blur the image to create a new image, referred to henceforth by the given
      destination name.

6. `sharpen image-name dest-image-name`:
    * sharpen the image to create a new image, referred to henceforth by the
      given destination name.

7. `greyscale image-name dest-image-name`:
    * Create a greyscale image using the filtering matrix, and refer to it
      henceforth in the program by the given destination name.

8. `sepia image-name dest-image-name`:
    * Create a sepia image using the filtering matrix, and refer to it
      henceforth in the program by the given destination name.

___

## Graphical User Interface (GUI) Instructions;

* ![GUI](./running%20GUI.png)

    * The GUI contains all the buttons to allow the user to manipulate the
      image, similarly to what can be achieved in the script file or interactive
      text mode. Visually, the user will be able to see the image as they apply
      the operations.

    * The left side contains the options to load an image, save an image, and view
      the histogram of the image.
    * The bottom of the window will display messages to the user, such as
      whether the operation was successful or not.
        * ![Messages](./messages.png)

    * User must load an image before applying any other operations.
        * ![Load](./load%20image.png)
        * ![Loaded](./loaded%20image.png)

    * The right side contains the options to manipulate the image. User may
      adjust the brightness, greyscale based on a selected component, or apply a
      filter to the image, such as blur, sharpen, greyscale, or sepia.

    * To adjust the brightness of an image, the user needs to select a value on
      the slider bar and click the "Brightness" button. Positive values will
      brighten the image, while negative values will darken the image.
        * ![Brightness](./brightness%20slider.png)

    * To greyscale based on a selected component, the user needs to select a
      component from the drop-down menu and click the "Greyscale" button.
        * ![Greyscale](./component%20greyscales.png)
        * ![Greyscaled](./grey%20selections.png)

    * For filters, the user simply just clicks the button of the filter they
      want to apply to the image while an image is loaded.
    * The histogram may also be viewed while an image is loaded.
        * ![Histogram](./histogram.png)

    * The user may save the image at any time by clicking the "Save" button. The
      supported file types are `.png, .jpg, .jpeg, and .ppm`.
        * ![Save](./save.png)

___

# Change log;

## HW08;

* This was the first iteration of the program that allowed for loading/saving of
  ASCII PPM files. Transformations & manipulation of images allowed for
  brightening, darkening, and greyscaling of images based on the value, luma,
  intensity, or individual RGB components of the image.
* The program supported text-based scripting line-by-line, where the user
  could input commands to the program to load/save images, and to manipulate the images, all while
  handling errors.

## HW09 changes;

* Added support for loading/saving of PNG, JPG, and JPEG files.
* Added support to apply filters to an image using the following giving filtering matrices:
    * blur
    * sharpen
    * greyscale
    * sepia
* Added support for command-line arguments to run the program with a script file
  that contains commands to run the program.

## HW10 changes;

* Implements the view component of the program.
* Added support for a graphical user interface (GUI) to display a window
  allowing the user to load an image from a file and visually see the image. The
  GUI contains buttons to allow the user to manipulate the image, such as
  brightening, darkening, greyscaling, and applying filters to the image. The
  user may also view a histogram of the image's RGB values.
* Added support for a command-line argument to run the program with either a
  script file, text-based line-by-line scripting, or opening the GUI.

___

## High-level design;

The program follows the Model-View-Controller (MVC) design pattern, comprising three main
components:

1. Model: Responsible for manipulating images, consisting of the ImageModel interface,
   ImageModelImpl class, and various packages like pixel, image, and transform.

2. Controller: Handles user commands and interactions, consisting of the Controller interface,
   ControllerImpl, InteractiveControllerImpl classes, and packages for commands and IO operations.

3. View: Represents the user interface, comprising the View interface, ViewImpl class, and packages
   for GUI components.

### Model;

Within the model component, there are 3 packages: pixel, image, & transform.
These packages and their respective classes work with the ImageModel class to
manipulate the image.

* **MODEL PACKAGE**
    * ImageModel (interface): This interface represents the model of the program.
      It has methods to add an image to the model, and to get the image from the
      model.
        * `void addImage(String id, ImageState image);`, `ImageState getImage(String id);`
    * ImageModelImpl (class): This class implements the ImageModel interface. It
      represents the model of the program. It has methods to add an image to the
      model, and to get the image from the model.

* **PIXEL PACKAGE**
    * Pixel (interface): This interface represents a pixel in an image, and
      extends PixelState interface. It has methods to set the red, green, and blue
      values of the pixel.
        * `void setR(int R)`, `void setG(int g);`, `void setB(int b);`, `void setRGB(int[] rgb);`
    * PixelState (interface): This interface represents the state of a pixel. It
      has methods to get the red, green, and blue values of the pixel. By
      separating the pixel state from the pixel, the view can access the pixel
      state without being able to change the pixel state.
        * `int getR();`, `int getG();`, `int getB();`, `int[] getRGB();`, `double getAlpha();`
    * PixelImpl (class): This class implements the Pixel interface. It represents
      a pixel in an image.

* **IMAGE PACKAGE**
    * Image (interface): This interface represents an image. It has methods to set
      the pixels inside an image.
        * `void setPixel(int x, int y, int r, int g, int b);`
    * ImageState (interface): This interface represents the state of an image. It
      has methods to get the pixels inside an image. By separating the image state
      from the image, the view can access the image state without being able to
      change the image state.
        * `int getWidth();`, `int getHeight();`, `int getRedChannel(int x, int y);`,
          `int getGreenChannel(int x, int y);`, `int getBlueChannel(int x, int y);`
    * ImageImpl (class): This class implements the Image interface. It represents an image.

* **TRANSFORM PACKAGE**
    * This package includes all the transformation classes, such as
      `BrightenTransformation`, `GreyscaleRedTransformation`,
      `GreyscaleGreenTransformation`, `GreyscaleBlueTransformation`,
      `GreyscaleIntensityTransformation`, `GreyscaleLumaTransformation`, and
      `GreyscaleValueTransformation`.
    * It also includes the abstract class `BaseMethods` that contains the
      `clamp(int value)` method, which is used by all the transformation/filter
      classes to clamp the RGB values of the pixels.

* **FILTER PACKAGE**
    * This package includes all the filtering such as `Filtering (interface)`,
      `FilteringImpl (class)`, and `ColorFilteringImpl (class)`.
        * They all for the application of filter matrixes to images to create new
          images. The FilteringImple and ColorFilteringImpl differ on the math &
          type of matrix implemented.

### Controller;

Withing the controller component, there are 4 packages: controller, io, &
commands. These packages and their respective classes work with the ImageModel
class to manipulate the image as the user commands.

* **CONTROLLER PACKAGE**
    * Controller (interface): This interface represents the controller of the
      program. It has methods to run the controller.
        * `void runController();`
    * ControllerImpl (class): This class implements the Controller interface. It
      allows for the user to interact with the program to use the operations,
      using simple text-based scripting.
    * InteractiveControllerImpl (class): This class implements the Controller
      interface & ViewListener interface. It handles evens passed from the view,
      and runs the appropriate commands and operations.

* **IO PACKAGE**
    * ImageLoader (interface): This interface represents the image loader of the
      program. It contains methods to load from a file and to save it as an
      ImageState.
        * `ImageState run();`
    * ImageSaver (interface): This interface represents the image saver of the
      program. It contains methods to save an ImageState to a file.
        * `void run();`
    * PPMImageLoader (class): This class implements the ImageLoader interface. It
      loads an image from a file and returns it as an ImageState.
    * PPMImageSaver (class): This class implements the ImageSaver interface. It
      saves an ImageState to a file.
    * BaseIOMethods (abstract class): This abstract class contains methods that
      are used by both the PPMImageLoader and PPMImageSaver classes. It contains
      methods to get the file extension of a file, and to read an image file.
        * `String getFileExtension(File file);`, `ImageState readImageFile(File file);`
    * BufferedImageLoader (class): This class implements the ImageLoader
      interface. It loads an image from a file and returns it as an ImageState.
    * BufferedImageSaver (class): This class implements the ImageSaver interface.

* **COMMANDS PACKAGE**
    * Command (interface): This interface represents a command. It has methods to
      run the command.
        * `void run(Scanner scanner, ImageModel model);`
    * LoadPPMCommand (class): This class implements the Command interface. It
      loads an image from a file and adds it to the model.
    * SavePPMCommand (class): This class implements the Command interface. It
      saves an image to a file.
    * BrightenCommand (class): This class implements the Command interface. It
      brightens an image by the given increment.
    * ValueComponentCommand (class): This class implements the Command interface.
      It creates a greyscale image with the value, luma, intensity, RGB component
      of the image.
    * BlurFilterCommand (class): This class implements the Command interface.
      It creates a blurred image.
    * SharpenFilterCommand (class): This class implements the Command interface.
      It creates a sharpened image.
    * GreyscaleFilterCommand (class): This class implements the Command interface.
      It creates a greyscale image.
    * SepiaFilterCommand (class): This class implements the Command interface.
      It creates a sepia image.

### View;

Within the view component, there is 1 package: view. This package and its
respective classes work with the model and controller components to display the
image to the user, and to display error messages to the user.

* **VIEW PACKAGE**
    * Canvas (interface): This interface represents a canvas for an image
      processing program. The canvas displays the image loaded by the user. When
      the user applies a transformation to the image, the canvas displays the
      transformed image and updates the image state. One image is displayed at a
      time.
        * `void setImage(BufferedImage image);`
    * CanvasImpl (class): This class implements the Canvas interface. It
      represents a canvas for an image processing program. The canvas displays the
      image loaded by the user. It extends the JPanel class and implements the
      Canvas interface.
        * `void setImage(BufferedImage image);`,
          `void paintComponent(Graphicsg);`, `void getPrefferedSize();`
    * Histogram (class): This class implements the Canvas interface, and
      represents a histogram for an image processing program. The histogram
      displays the RGB values of the image loaded by the user as a bar graph.
        * `void setImage(BufferedImage image);`, `void paintComponent(Graphicsg);`,
          `void calculateHistogram(BufferedImageimage);`, `void getColorForChannel();`
    * ViewListener (interface): This interface represents the view listener of the
      program. It has methods to handle events passed from the view.
        * `void handleLoadEvent();`, `void handleSaveEvent();`, `void handleBlurEvent();`,
          `void handleSharpenEvent();`, `void handleComponentGreyscaleEvent(int component);`, `void handleSepiaEvent();`,
          `void handleBrightenEvent();`, `void handleFilterGreyscaleEvent();`
    * View (interface): This interface represents the view of the program. It has
      methods to display the image to the user, and to display error messages to
      the user.
    * ViewImpl (class): This class implements the View interface.
        * `void setImageView(BufferedImage image);`,
          `void addViewListener(ViewListener listener)`, `void requestFrameFocus();`,
          `void emitLoadEvent();`, `void emitSaveEvent();`,
          `void emitBrightenEvent(int value);`,
          `void emitComponentGreyscaleEvent(int component);`,
          `void emitBlurEvent();`, `void emitSharpenEvent();`,
          `void emitFilterGreyscaleEvent();`, `void emitSepiaEvent();`,
          `void emitHistogramEvent();`, ` void setVisible(boolean b);`,
          `void displayMessage(String message);`

___

## Image Sources & Citations;

* Image Source: Kirlin, P. (2019). Four by Four [Image]. Retrieved from
  https://www.cs.rhodes.edu/~kirlinp/courses/cs1/f19/projects/proj8/fourbyfour.ppm

The following images were taken or drawn by me,
and scaled down to small pixel sizes. I am authorizing its use for this assignment.

* Image Source: Linda, Q. (2023). Pigeon [Image].
* Image Source: Linda, Q. (2023). BigPigeon [Image].
* Image Source: Linda, Q. (2023). BiggestPigeon [Image].
* Image Source: Linda, Q. (2023). Anya [Image].
* Image Source: Linda, Q. (2023). Bee [Image].
* Image Source: Linda, Q. (2023). Cover [Image].

___

## Thanks for reading! :)

































