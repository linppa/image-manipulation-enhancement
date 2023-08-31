package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import model.ImageModel;

/**
 * This class represents the GUI view for the image processing program. It implements the View
 * interface and extends JFrame. The view is responsible for displaying the image and handling
 * user interactions within the GUI by emitting events to the controller to update the model.
 */
public class ViewImpl extends JFrame implements View {
  private final ImageModel model;
  private final JPanel mainPanel;
  private BufferedImage currentImage;
  private final List<ViewListener> listenersToNotify;
  private final CanvasImpl canvas;
  private final Histogram histogramPanel;
  private final JLabel messageText;
  private JButton saveButton;
  private JButton loadButton;
  private JComboBox<String> componentGrey;
  private JButton componentGreyscaleApply;
  private JSlider brightenSlider;
  private JButton brightenApply;
  private JButton blurButton;
  private JButton sharpenButton;
  private JButton filterGreyscaleApply;
  private JButton sepiaButton;
  private JButton histogramButton;

  /**
   * Constructs a view object. It takes in a model and initializes the view's fields. Additionally,
   * it sets up all the graphical components of the user interface by utilizing Java Swing.
   *
   * @param model the model, cannot be null
   * @throws NullPointerException if the given model is null
   */
  public ViewImpl(ImageModel model) throws NullPointerException {
    this.model = Objects.requireNonNull(model);
    this.listenersToNotify = new ArrayList<>();
    this.currentImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    this.histogramPanel = new Histogram();

    // ----- MAIN PANEL ----- //
    this.mainPanel = new JPanel(new BorderLayout());
    setContentPane(mainPanel);
    setSize(1200, 600);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setTitle("Graphical Image Manipulation & Enhancement");

    // center
    this.canvas = new CanvasImpl();
    JScrollPane canvasScroll = new JScrollPane(canvas);
    canvasScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    canvasScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    mainPanel.add(canvasScroll, BorderLayout.CENTER);

    // bottom
    this.messageText = new JLabel(" ");
    this.messageText.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    this.messageText.setHorizontalAlignment(JLabel.CENTER);
    mainPanel.add(this.messageText, BorderLayout.SOUTH);

    // ----- LEFT & RIGHT PANEL ----- //
    // save & load
    buildLeftPanel();
    // image manipulation
    buildRightPanel();

    // set action commands & listeners
    setActionCommands();
    addActionListeners();
    this.addKeyListener(this);
    this.setFocusable(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "save":
        emitSaveEvent();
        break;
      case "load":
        emitLoadEvent();
        break;
      case "brighten":
        int value = brightenSlider.getValue();
        emitBrightenEvent(value);
        break;
      case "componentGreyscale":
        int component = componentGrey.getSelectedIndex();
        emitComponentGreyscaleEvent(component);
        break;
      case "blur":
        emitBlurEvent();
        break;
      case "sharpen":
        emitSharpenEvent();
        break;
      case "filterGreyscale":
        emitFilterGreyscaleEvent();
        break;
      case "sepia":
        emitSepiaEvent();
        break;
      case "histogram":
        emitHistogramEvent();
        break;
      default:
        try {
          throw new IllegalArgumentException("Invalid action command");
        } catch (IllegalArgumentException ex) {
          displayMessage(ex.getMessage());
        }
    }
  }

  @Override
  public BufferedImage getImageFromView() {
    return this.currentImage;
  }

  @Override
  public void setImageView(BufferedImage image) throws NullPointerException {
    this.currentImage = Objects.requireNonNull(image);
    canvas.setImage(image);
  }

  @Override
  public void addViewListener(ViewListener listener) {
    listenersToNotify.add(listener);
  }

  @Override
  public void requestFrameFocus() {
    this.requestFocus();
  }

  @Override
  public void emitLoadEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleLoadEvent();
    }
  }

  @Override
  public void emitSaveEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleSaveEvent();
    }
  }

  @Override
  public void emitBrightenEvent(int value) {
    for (ViewListener listener : listenersToNotify) {
      listener.handleBrightenEvent(value);
    }
  }

  @Override
  public void emitComponentGreyscaleEvent(int component) {
    for (ViewListener listener : listenersToNotify) {
      listener.handleComponentGreyscaleEvent(component);
    }
  }

  @Override
  public void emitBlurEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleBlurEvent();
    }
  }

  @Override
  public void emitSharpenEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleSharpenEvent();
    }
  }

  @Override
  public void emitFilterGreyscaleEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleFilterGreyscaleEvent();
    }
  }

  @Override
  public void emitSepiaEvent() {
    for (ViewListener listener : listenersToNotify) {
      listener.handleSepiaEvent();
    }
  }

  @Override
  public void emitHistogramEvent() {
    try {
      model.getImage("currentImage");
    } catch (IllegalArgumentException e) {
      displayMessage("Please load an image before generating a histogram.");
      return;
    }
    // give histogram panel current image
    histogramPanel.setImage(currentImage);
    // display in pop-up window
    JOptionPane.showMessageDialog(this, histogramPanel, "Histogram", JOptionPane.PLAIN_MESSAGE);
  }

  @Override
  public void displayMessage(String message) {
    this.messageText.setText(message);
  }

  @Override
  public void keyTyped(KeyEvent e) {
    // do nothing
  }

  @Override
  public void keyPressed(KeyEvent e) {
    // do nothing
  }

  @Override
  public void keyReleased(KeyEvent e) {
    // do nothing
  }

  /**
   * Creates border with title and spacing around the edges. Used for the left and right panels to
   * make the GUI look nicer.
   *
   * @param message title
   * @param top     spacing
   * @param left    spacing
   * @param bottom  spacing
   * @param right   spacing
   * @return border
   */
  private Border titleSpacedBorder(String message, int top, int left, int bottom, int right) {
    TitledBorder titledBorder = BorderFactory.createTitledBorder(null, message,
            TitledBorder.CENTER, TitledBorder.TOP);
    Border emptyBorder = BorderFactory.createEmptyBorder(top, left, bottom, right);
    return BorderFactory.createCompoundBorder(titledBorder, emptyBorder);
  }

  /**
   * Sets the action commands for the buttons.
   */
  private void setActionCommands() {
    this.saveButton.setActionCommand("save");
    this.loadButton.setActionCommand("load");
    this.brightenApply.setActionCommand("brighten");
    this.componentGreyscaleApply.setActionCommand("componentGreyscale");
    this.blurButton.setActionCommand("blur");
    this.sharpenButton.setActionCommand("sharpen");
    this.filterGreyscaleApply.setActionCommand("filterGreyscale");
    this.sepiaButton.setActionCommand("sepia");
    this.histogramButton.setActionCommand("histogram");
  }

  /**
   * Adds action listeners to the buttons.
   */
  private void addActionListeners() {
    this.saveButton.addActionListener(this);
    this.loadButton.addActionListener(this);
    this.brightenApply.addActionListener(this);
    this.componentGreyscaleApply.addActionListener(this);
    this.blurButton.addActionListener(this);
    this.sharpenButton.addActionListener(this);
    this.filterGreyscaleApply.addActionListener(this);
    this.sepiaButton.addActionListener(this);
    this.histogramButton.addActionListener(this);
  }

  /**
   * Builds the left panel of the GUI, containing the save and load buttons.
   */
  private void buildLeftPanel() {
    JPanel leftPanel = new JPanel(new BorderLayout());
    leftPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    // grid of save & load buttons
    JPanel saveLoadButtons = new JPanel((new GridLayout(0, 1)));
    saveLoadButtons.setBorder(titleSpacedBorder("File: ", 20, 15, 20, 15));
    JPanel histogramButton = new JPanel((new GridLayout(0, 1)));
    histogramButton.setBorder(titleSpacedBorder("Histogram: ", 20, 15, 20, 15));

    // add save & load buttons
    this.loadButton = new JButton("Load Image");
    this.saveButton = new JButton("Save Image");
    this.histogramButton = new JButton("Histogram");
    saveLoadButtons.add(this.loadButton);
    saveLoadButtons.add(this.saveButton);
    histogramButton.add(this.histogramButton);

    // add buttons to left panel
    leftPanel.add(saveLoadButtons, BorderLayout.EAST);
    leftPanel.add(histogramButton, BorderLayout.SOUTH);

    // add save & load to main panel
    mainPanel.add(leftPanel, BorderLayout.WEST);
  }

  /**
   * Builds the right panel of the GUI, containing the transformation & filter buttons.
   * The brightness transformation utilizes a slider for the user to select the brightness value.
   * The component greyscale transformation utilizes a drop-down menu for the user to select
   * which component to greyscale.
   */
  private void buildRightPanel() {
    JPanel rightPanel = new JPanel(new GridLayout(0, 1));
    rightPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    // grid of transformation buttons
    JPanel transformationButtons = new JPanel((new GridLayout(0, 1)));
    JPanel componentButtons = new JPanel((new GridLayout(0, 1)));
    JPanel filterButtons = new JPanel((new GridLayout(0, 1)));
    transformationButtons.setBorder(titleSpacedBorder("Transformations: ", 20, 15, 20, 15));
    componentButtons.setBorder(titleSpacedBorder("Component Greyscales: ", 20, 15, 20, 15));
    filterButtons.setBorder(titleSpacedBorder("Filters: ", 20, 15, 20, 15));

    // add transformation buttons
    brightenApply = new JButton("Brightness");
    brightenSlider = new JSlider(JSlider.HORIZONTAL, -100, 100, 0);
    brightenSlider.setMajorTickSpacing(50);
    brightenSlider.setMinorTickSpacing(10);
    brightenSlider.setPaintTicks(true);
    brightenSlider.setPaintLabels(true);
    brightenSlider.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

    // combo box
    componentGrey = new JComboBox<>();
    componentGrey.addItem("Select Component:");
    componentGrey.addItem("Value");
    componentGrey.addItem("Red");
    componentGrey.addItem("Green");
    componentGrey.addItem("Blue");
    componentGrey.addItem("Luma");
    componentGrey.addItem("Intensity");
    componentGreyscaleApply = new JButton("Greyscale");
    blurButton = new JButton("Blur");
    sharpenButton = new JButton("Sharpen");
    filterGreyscaleApply = new JButton("Greyscale");
    sepiaButton = new JButton("Sepia");

    // add buttons to menu
    transformationButtons.add(brightenSlider);
    transformationButtons.add(brightenApply);
    componentButtons.add(componentGrey);
    componentButtons.add(componentGreyscaleApply);
    filterButtons.add(blurButton);
    filterButtons.add(sharpenButton);
    filterButtons.add(filterGreyscaleApply);
    filterButtons.add(sepiaButton);

    // add menu to right panel
    rightPanel.add(transformationButtons);
    rightPanel.add(componentButtons);
    rightPanel.add(filterButtons);
    // add menu to main panel
    mainPanel.add(rightPanel, BorderLayout.EAST);
  }
}
