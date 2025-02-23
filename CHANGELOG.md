### [v1.3.5-stable](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.3.5-stable-free)
- Note: The recommended JAVA version for DBV3 is now Java 17.
- Note: All packaged installers now use a Java 17 Runtime instead of Java 11
- Added: Sketch Waves PFM, another Sketch PFM variation which uses Sin/Cos/Tan waves to direct the sketched lines, it's in Beta (Premium Only)
- Added: Sketch Layers, allows you to layer the results on multiple PFMS on top of each other and optionally pass the lightned image between them to create new unique styles (Premium Only), thanks to HanzPetrov for all his hardwork on this!
- Added: Projects saved will now store the current drawing state, allowing you to reload projects exactly where you left off. Versions will also store the drawing state, so you can switch between variations instantly. (Premium Only)
- Added: New GCode Options, "Curve Flatness", "Center Zero Point" and "Comment Type"
- Added: US Paper Sizes to Drawing Area presets. ANSI, ARCH and US Letter/Legal/Executive.
- Fixed: OpenGLRenderer will now work as intended on MacOS!
- Fixed: Images randomly failing to load, fixes issue where images might need to be imported twice.
- Fixed: Viewport scrolling on some hardware configurations.
- Fixed: Serial Connection control panel not opening
- Fixed: MacOS .pkg Installers not running
- Changed: Delayed OpenGL Initialization to speed up load times and prevent crashes at start up.
- Changed: Simplified logging, removing redundant information
- Changed: The first time you run OpenGL after restarting DBV3 it will take a moment to activate.
- Changed: Improved OpenGLRenderer compatibility to favour faster implementations.

### [v1.3.4-stable](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.3.4-stable-free)
- Fixed: Windows .zip and .exe Installers not running for some configurations.

### [v1.3.3-stable](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.3.3-stable-free)
- Added: New Installer Type: .pkg for MacOS Users, this is considered Beta!
- Added: "Set As Default" option to all preset menus, the selected default preset will load when DBV3 is started.
- Added: Export / Import Preset Dialogs, the export dialog gives you the option of opening the folder you saved the preset too.
- Changed: Versions will now be saved in project files.
- Fixed: For now by default the OpenGLRenderer will be disabled on MacOS to prevent issues at startup, if you think your MAC is compatible you can change "enableOpenGLMacOS" to "true" in the config but this may cause DBV3 to not start properly.
- Fixed: Voronoi Circles will now produce a useful output if it's stopped early.
- Fixed: Half-pixel offset showing up on the following Display Modes: "Image", "Reference", "Lightened"

### [v1.3.2-stable](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.3.2-stable-free)
- Hot-Fix: Exports missing some geometries, in particular Voronoi Triangulation.
- Fixed: Imported Presets not saving properly.

### [v1.3.1-stable](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.3.1-stable-free)
- Added: New option in config "disableOpenGLRenderer", some systems are incompatible with the Hardware Accelerated Renderer, if DrawingBotV3 fails to start, try changing this value to true.
- Fixed: Batch Processing on MAC and Linux will now use the correct File Seperator.
- Fixed: SVG Exporting
- Fixed: Drawing Pen / Drawing Set preset loading.

### [v1.3.0-stable](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.3.0-stable-free)
- DrawingBotV3 now comes in two version **Free** and **Premium**, find out more [here](https://drawingbotv3.ollielansdell.co.uk/premium-version/)
- Added: New Hardware Accelerated Renderer, to activate go to Display Mode : "Drawing (Hardware Accelerated)"
- Added: "Squiggle Deviation" Slider to Sketch PFMs, this allows you to decide how far a squiggle is allowed to deviate in brightness before it is ended prematurely, this has the result of making shorter squiggles which are more accurate and less likely to cross over brighter areas of the image. 
- Added: "Unlimited Tests" Option to a few Sketch PFMs, this will run as many tests as required to find the "best" line possible, resulting in more accurate drawings with longer processing times.
- Added: "Curve Refinement" Option to Catmull-Roms, this will add an additional pass after the curves have been found to see if minor adjustments to the curves points will improve the accuracy.
- Added: Blend Mode compatibility to PDF export - HanzPetrov
- Added: Live updating "Position", above the viewport you'll be able to see where you mouse is relative to your drawings size!
- Changed: Batch Processing will now display the current drawing, and give more useful progress messages and updates
- Changed: Sketch Curves/Beziers/Catmull-Roms now render as individual curves rather than grouped shapes, allowing finer control over the curves rendered, and resulting in a more accurate mapping of the colour values to the pens enabled.
- Changed: Shading Options are now also available on Sketch Shapes and Sketch Sobel Edges
- Changed: Improved the accuracy of Sketch PFMs
- Changed: Sketch PFMs now use the Bresenham Midpoint Circle Algorithm to reduce the number of neighbour tests required.
- Changed: Allow Customizable Drawing Pen & Drawing Set Categories rather than just "User"
- Changed: Voronoi Default Settings: Point Count now defaults to 50000 instead of 10000 and Luminance Power / Density Power default to 3 instead of 5
- Fixed: When creating new Pen Presets, the type name will be used instead of defaulting to "User".
- Fixed: Image Filters losing their settings when using "Edit Settings" and not hitting apply.
- Fixed: Image Filters not updating when an earlier filter in the chain is disabled.
- Fixed: Sobel Edges PFM not working properly on images with Alpha Channels
- Fixed: Dirty Border Filter not working properly on images with Alpha Channels
- Fixed: CMYK Separation not working properly on images with Alpha Channels
- Fixed: View menu will now work as expected and not glitch out the settings panels
- Fixed: Export settings panels disappearing after being displayed while exporting for HPGL, Image Sequences and GCode
- Fixed: "Special" Pens can now be saved in Drawing Set Presets without losing their functionality.

### [v1.2.3-stable](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.2.2-stable)
- Fixed: Drawings randomly failing to export when Path Optimization is enabled.
- Fixed: GCode Bezier Curves not matching the drawing properly. -Triod-project
- Changed: Geometries will now start from the middle of the pixel and not the top left - HanzPetrov

### [v1.2.2-stable](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.2.2-stable)
- Fixed: Fixes UI Hanging after re-processing image

### [v1.2.1-stable](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.2.1-stable)
- Added: Option to specify the PPI to render Images/Animations at higher resolutions, in the Export Settings menu  - HanzPetrov
- Added: Replaced the Displayed Shapes slider with a Range Slider
- Added: "Apply to export" checkbox to enable the Ranges selected on the exported files
- Added: A "Task Monitor" to monitor background tasks, accessed via File/Open Task Monitor
- Changed: Removed the "Zoom In" and "Zoom Out", the viewport can now be zoomed with the scroll wheel.
- Changed: Line Merging/Sorting Algorithms now use an STRTree which results in better optimisation in 1/10th of the time, particularly on Voronoi Triangulation PFMS.
- Changed: The progress of Optimization Algorithms will now be shown in the progress bar.
- Changed: The default "Curve Flatness" for HPGL from 6 to 0.1
- Changed: The default "target pen width" from 0.5 to 0.3
- Changed: The default rotation for HPGL from 90 to 270, when using AUTO which requires rotation
- Changed: Improved the monitoring of progress during Geometry Optimization
- Fixed: Bug where the Serial Connection Menu would fail to load when less than 2 Serial Ports were available.
- Fixed: The viewport will now behave as expected when zooming in and out.

### [v1.2.0-stable](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.2.0-stable)
- Added: HPGL Export - Export your images to HPGL for sending to plotters.
    - You can configure your HPGL files with configurable Hard-Clip limits, X-Axis Mirror, Y-Axis Mirror, X-Axis Alignment, Y-Axis Alignment, Rotation, Curve Flatness, Pen Velocity and the initial Pen Number.
    - If you're inputted the Hard-Clip limits you can use "Auto" rotation, to position your image correctly for you so you don't need to worry about if it's landscape or portrait!
    - Choose from presets for different plotter models and paper sizes
    - When the export is complete you'll see a dialog indicating where pens should be loaded and the size of the HPGL File.
    - If the drawing exceeds the Plotter's Hard-Clip limits you'll be notified.
- Added: Serial Port Connection for Plotters! Send HPGL Files and commands directly from DrawingBotV3.
    - Allows you to set the Serial Port, Baud Rate, Data Bits, Stop Bits, Parity and Flow Control
    - Also including monitoring of the Progress, Bytes Sent, Elapsed Time, Remaining Time and the Plotters own buffer.
    - Sending can also be paused when needed and resumed.
    - Ability to detect the plotter being used and apply the recommended settings!
    - The serial port connection runs in a seperate thread so you can continue using DrawingBot while your Drawing is plotting.
    - It can also be used to send files not generated with DrawingBot and in fact will stream any .txt file over the serial port!
- Added: Video Import - You can now import videos and process every frame automatically
    - When you choose your export option this will apply to every frame in the video
- Added: Configurable Canvas Colour - HanzPetrov
- Added: Right-Click menu for Drawing Styles
- Changed: Stroke end-caps will now default to ROUND - HanzPetrov
- Changed: PDF exports will now match the print resolution not the image resolution - HanzPetrov
- Fixed: Disabled pens will not output a file when exporting "per/pen"
- Fixed: Mosaic Custom being uneditable when loaded from a preset
- Fixed: Pen Width not being loaded with projects, or preventing them loading entirely.

### [v1.1.1-stable](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.1.1-stable)
- Fixed: Previous iterations of Voronoi Diagrams showing up in SVG exports. (Random dots on the export)

### [v1.1.0-stable](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.1.0-stable)
- Added: Three New Path Finding Modules Mosaic Custom, Voronoi Tree and Voronoi TSP.
  - "Mosaic Custom" - Allows you to create your own Custom Mosaic, using an image mask. 
    This opens up many creative possibilities combining the effects of multiple PFMs to create more complex pieces. 
    This is very easy to do, you first need to create an image mask in an image editor which has the same resolution as the original image which contains a unique block colour for each drawing style you plan to use.
    Then you can import this mask as one of the settings, and for each Drawing Style define a "Mask Colour" which matches the one you used in the mask image.
    You can also feather the mask in the settings.
  - "Voronoi Tree" - Creates a "Minimum Spanning Tree" using Prim's algorithm, using the points generated from a Weighted Voronoi Diagram, it creates artwork similar to the classic TSP art but in a fraction of the time.
  - "Voronoi TSP" - Creates a solution to the "Travelling Salesman Problem" for the points generated from a Weighted Vornoi Diagram, if you're serious about using this PFM with a high point count expect to wait many hours or days to get the final result, optimisations are very welcome! 
    There are currently 3 algorithms, a Lazy 2-Opt Approach which is very fast and inaccurate, then an implementation of the classic Lin-Kernighan, and an attempt to implement the Lin-Kernighan-Helsgaun algorithm. 2-Opt is the most stable and the one I recommend.
- Added: CMYK Configuration, next to the colour seperation drop-down you now have the option to configure the weighting of the CMYK plot to allow you to fine tune the density to your specific pens, as many users reported their K layer being too strong, the default for K is now x0.75. This option works with every PFM.
- Added: Video Exporting for Animations in both H.264 and ProRes 422.
- Changed: "Randomise" will now produce more stable results and should be able to run in most instances
- Changed: The min/max of a few key variables have been changed.
- Changed: Values can now be set outside of the range of the min/max for more advanced users, in some instances this may cause the plot to crash or not start at all.
- Changed: Some PFMs which had optimisation disabled by default will now have as a minimum geometry sorting enabled to reduce plotting times
- Changed: Mosaics now optimise the outputs of each tile individually to speed up optimisation times.
- Changed: Raster export dimensions will now be multiples of 2
- Fixed: Padding not being saved properly with Drawing Area presets.
- Fixed: De-activated pens being re-activated in presets
- Fixed: Voronoi Circles and Voronoi Diagram PFMs will now stay within the bounds of the image.
- Fixed: A bug where videos would not export if either of the image dimensions are odd.


### [v1.0.16-stable](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.0.16-stable)
- Added: Text to display the current images size, and the size it's being plotted at.
- Added: Rotate / Flip Options for images in Image Processing
- Fixed: Issue where the viewer would zoom into the top left corner of the image after pre-longed use.
- Fixed: Drawing Pens / CMYK Settings not loading correctly with project files.
  - Changed: Renamed the "Pre-Processing" tab to "Image Processing"
  - Fixed: Image Rendering - Blend Modes will now create a more accurate representation on exports.
- Fixed: PNG export accuracy, they will now match the JPG exports and only be transparent if the blend mode is "Normal".
- Fixed: Image Rendering / Image Sequence Rendering - Rendering will now be more accurate when using blend modes or CMYK seperation
- Fixed: Image Sequence Rendering - Fixes a rare glitch where random lines may appear on an animation.
- Fixed: Rare bug where Colour Sampling in curve modes would result in the final line being completely transparent.

### [v1.0.15-stable](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.0.15-stable)
- Added: Two New Path Finding Modules for creating Mosaics!
    - "Mosaic Rectangle" - Creates a mosaic of rectangles out of a set of user configurable styles.
    - "Mosaic Voronoi" - Creates a mosaic based on the output of a weighted Voronoi Diagram from a set of user configurable styles.
    - The "User Configurable styles" can have a "weighting" to affect their distribution, there is no limit on the number of different styles per mosaic.
    - Please note: Using Voronoi PFMs for drawing styles can be tempremental.
- Added: Version Control! You can now save your favourite versions of your project as you go and experiment with settings without losing your favourites.
    - There is a "Save Version" button next to the plotting controls, click this when you create something you like, you can then access and reload / save these versions from the "Version Control" panel, which will show a preview of the version the date it was created and the name of the path finding module used.
- Added: Project Loading & Saving! 
    - You can now save your projects and reload them as ".drawingbotv3" files from the "File Menu" - you can also save versions in this way.
    - This saves all elements of the project, like Drawing Size, PFM Settings, Pen Settings & also all of the versions you've created!
    - It doesn't however save the image used to keep the files small but it will save the path to the image used and reload it if it's still available. 
- Fixed: Default presets become replicated when saving new presets.
- Fixed: Broken Sketch PFM Presets, they will now behave as intended again.

### [v1.0.14-stable](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.0.14-stable)
- Added: Image Sequences for creating animations of your creations! The duration can be change in Export Settings / Image Sequences
- Added: GCode "Start Layer" & "End Layer" custom commands
- Fixed: The new curve PFMs will now bypass plot optimization automatically to avoid curve flattening.
- Fixed: GCode exports will now include bezier curves, though this requires more testing.

### [v1.0.13-stable](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.0.13-stable)
- Added: Four New Path Finding Modules! All of which use much more accurate "Bresenham" calculations this results in longer processing times.
    - "Sketch Quad Beziers" - Builds the image out of the darkest quadratic curves. By first finding the darkest line and finding the darkest control point.
    - "Sketch Cubic Beziers" - Builds the image out of the darkest cubic curves. By first finding the darkest line, and then finding the darkest combination of control both points.
    - "Sketch Catmull-Roms" - Builds the image out of the darkest catmull-rom splines. This works by finding the best possible curve over the next two segments.
    - "Sketch Shapes" - Builds the image out of either Squares of Ellipses.
  
- Changed: The original "Sketch Curves PFM" has had a major revamp and now works almost as well as Catmull Roms, however it uses more basic Bresenham calculations so has a reduced accuracy, but does result in a much faster processing time.
- Fixed: An issue where when hitting "Reset View", it sometimes had to be hit again.
- Fixed: An issue where zooming would behave in strange ways.
- Fixed: Issue where either the last or first shape wasn't drawn, primarily in curve modes.

### [v1.0.12-stable](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.0.12-stable)
- Added: Text fields for Image Filter options
- Fixed: GCode Export, files will now have the correct offsets and orientation.

### [v1.0.11-stable](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.0.11-stable)
- Added: "vpype" integration, automatically open your drawing in vpype + run commands from presets with "File / Export to vpype"
- Added: New GCode Settings completely customisable GCode Commands / Orientation to support many more types of plotter.
- Added: You can now drag + drop images into the viewer
- Changed: Plotting task will now give more status updates than before.
- Changed: Export options will be greyed out until an image is imported.

### [v1.0.10-beta](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.0.10-beta)
- Added: DrawingBotV3 now has [documentation](https://drawingbotv3.readthedocs.io)!
- Added: The name of the current image file will be displayed in the title of the window.
- Changed: Line density's maximum value will now result in target brightness of 253.5, not 250
- Fixed: Inaccuracies in plotting image sections smaller than the line length.
- Fixed: Progress updates when exporting files

### [v1.0.9-beta](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.0.9-beta)
- Added: New Presets for "Sketch Squares" - "Waves" & "Triangles"
- Added: CMYK Seperation is now multi-threaded & and gives an accurate live preview
- Added: If the "CMYK Drawing Set" is selected & CMYK isn't activated a dialog will appear to apply the settings automatically.
- Changed: CMYK Seperation will now use opaque pens on some PFMs.
- Fixed "Sketch Squares" PFM it will now work as expected and have different styles to "Sketch Lines PFM"
- Fixed "Circle Size" not working as expected in "Voronoi Circles" + prevented small circles being invisible.

### [v1.0.8-beta](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.0.8-beta)
- Added: Five New Path Finding Modules!
    - "Sketch Sobel Edges PFM" - Works like the "Sketch Lines PFM" but uses Edge Detection as well as Brightness Sampling, which can be adjusted to create stylised drawings with emphasised contours.
    - "Voronoi Triangulation" - Draws triangles between the centre points of a Weighted Voronoi Diagram.
    - "Voronoi Stippling" - Draws filled circles at the centre points of a Weighted Voronoi Diagram, each circle is scaled to the average brightness of the containing cell
    - "Voronoi Circles" - Draws inscribed circles into the cells of a Weighted Voronoi Diagram
    - "Voronoi Diagram" - Draws the Voronoi Diagram by itself.
- Added: New "Rescale to Pen Width" option in "Drawing Area" - which helps in optimising the line density of your plots, you must enter the "width" & "height" of the drawing area for this to be applied. 
- Added: New Special Pens for use for screen only outputs "Original Red" , "Original Green", "Original Blue"
- Added: New Pen Distribution Type Setting, options include "Even", "Even Weighted (Default)", "Random", "Random Weighted", "Preconfigured", "Single Pen" - Path Finding Modules will select there recommended distribution type the first time they are run.
- Added: New "Rotate" button in Drawing Area to quickly swap Width / Height.
- Added: "Centre X", "Centre Y" & "Spiral Size" to "Spiral PFM", which allows you to move the start position of the spiral and change it's size.
- Changed: The rendering quality of lines created from low quality images has been improved massively, the images will look the same, exports will still be lower res / match the input resolution.
- Changed: The default preset for "Sketch Lines PFM" now has shading disabled by default, there is a new preset called "Simple Shading" which performs like the original.
- Changed: "Lock" has been renamed to "Randomise Exclude" and hidden by default, as it was misleading for new users and is rarely needed.
- Changed: Renamed setting "Desired brightness" to "Line density", instead of being an arbitary number, the images brightness/density is a percentage where any value is valid the default is 75%
- Changed: Renamed setting "Enable Shading" to "Shading", it behaves the same
- Changed: Renamed setting "Squiggle to shading" to "Shading Threshold", instead of being an arbitary number shading now kicks in when the processing has passed the specified percentage default is 50%.
- Changed: Renamed setting "Distance between rings" to "Ring Spacing", it behaves the same
- Changed: The settings for "Spiral PFM" now have more logical maximums.
- Fixed: Spiral PFM rendering the spiral off-screen.
- Fixed: "Min Line Length" would not always be respected and shorter lines would still be created, this has been fixed and so the defaults of some presets have also changed.
- Fixed: Rounding errors resulting in a less than uniform distribution of random angles, affected most path finding modules.
- Fixed: Line merging in Path Optimisation will now work more like expected.
- Fixed: Issue where completely black would not show up in the SVG output.
- Fixed: Image Rendering not updating when "Width" was changed.
- Note: If you have created any presets in older versions, they may break in this version.

### [v1.0.7-beta](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.0.7-beta)
-  Added: New Path Finding Module! "Sketch Curves PFM"
   - Performs the same as the default, but without shading and with Catmull Rom Splines instead of lines, you can configure the tension of the curves.
-   Added: Automatic SVG Optimisation, Line Simplification / Line Merging / Line Sorting
    - Can be configured in File / Export Settings / Path Optimisation
-   Added: Export Settings tab, to configure Path Optimisation / SVG Settings / GCode Settings
-   Added "Export Inkscape SVG" Option which supports Inkscape layers but might not work in other applications.
-  Added: New option "Rename layers (Pen1, Pen2...)" for Inkscape SVGs (compatible with the "Plot" function in Inkscape)
-  Added: "Filters" to the Menu Bar to allow for adding filters quickly.
-  Changed: GCode Export Settings have moved into the new "Export Settings tab"
-  Changed: Re-ordered export settings to better reflect file types used by plotters.
-  Fixed: Changing the Plotting Resolution will not affect the visual quality of the render and line size will be consistent
-  Fixed: Path Finding Modules will no longer gravitate to the corners of the image and will trace drawings more accurately.

### [v1.0.6-beta](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.0.6-beta)
-  Added: CMYK Colour Seperation / Special Drawing Sets - Print results will vary!
-  Added: Multi-Layer SVGs, SVGs now have seperate groups for each pen
-  Added: "Max Line Limit" option to PFM Sketch & PFM Squares
-  Added: Import / Export will now store the last used locations.
-  Added: "Open Configs Folder" option in the help menu.
-  Fixed: Massive Lag Spikes / the program becoming unresponsive
-  Fixed: Lag Spikes when changing to Special Drawing Sets / Changing Drawing Mode
-  Fixed: Pen stoke sizes will match custom values properly on exported SVGs
-  Fixed: Custom pen colours not rendering with the new colour / not saving when pressing "use"
-  Fixed: Logs not outputting correctly

### [v1.0.5-beta](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.0.5-beta)
- Added: New Image Filters, there are now over 60+ Image Filters!
- Added: Presets for Drawing Areas (A4 Paper / A3 Paper etc.) & GCode Settings
- Added: "Image" Display Mode, to view the imported image.
- Changed: Filters / Cropping will now update live in the "Image" display mode.
- Changed: Image exports now have Anti-Aliasing so will match the viewport more closely
- Changed: The viewport now has a max resolution of 4096 x 4096, exceeding this size can prevent some GPUs from working, image exports will still match the resolution of the input.
- Changed: Increased the maximum vram usage from 512MB to 1024 MB
- Fixed: SVG Outputs will now match the specified dimensions and will use a DPI of 96
- Fixed: The application hanging when importing large images
- Fixed: Issue with high resolution images plotting endlessly

### [v1.0.4-beta](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.0.4-beta)
- Added: Presets for Drawing Sets + Drawing Pens
  - Presets created for Drawing Sets / Pens will be saved to the "User" type.
- Added: Special Drawing Sets/Pens Original Colour / Original Grayscale
  - These will sample the average colour of the image and colour each line using these samples.
  - These samples are taken when the plot is being run and cached, this will slightly impact plotting performance, this caching can be disabled however by setting PFM setting "Cache Colour Samples" to "false".
- Added: "Padding" option to Drawing Area, with "Gang" check box
- Added: "Scaling Mode" option to Drawing Area: "Crop to fit", "Scale to fit" and "Stretch to fit"
- Added: "Lock" option for PFM Settings
  - Locking a setting will prevent it from being randomised, this will allow more creativity when using "randomise" allowing you to keep track of values you like.
  - Some settings are locked by default to prevent the plot failing (e.g. Plotting Resolution, Drawing/Shading Delta Angle)
  - Pressing "Reset" will still return the setting to default
- Added: Right-Clicking PFM Settings will now allow you to "Randomise" / "Reset" a single setting.
- Added: Check boxes to Drawing Pen selections to enable quicker configuration
- Added: Extra Buttons in Pen Settings for "Add", "Remove", "Duplicate", "Move Up" and "Move Down", you can still right click pens instead.
- Added: "Stroke" options for Drawing Pens to change the thickness of the
- Changed: Pressing ENTER will not start plotting anymore, avoiding accidentally overwriting your current plot.
- Changed: Pre-Processing will now be disabled by default
- Changed: Blend Modes won't lag so much when rendering
- Changed: Preset files have been overhauled to allow for the easy addition of even more preset types.
- Changed: GCode Settings has been split from "Drawing Area" and now appear at the bottom
- Changed: GCode Exporting has been re-written.
- Changed: GCode "Test Drawings" will now respect the enabled pens / allow seperate pen tests
- Changed: Removed "Import URL" option
- Changed: When exporting the default will now be the original files folder and name with the suffix "_plotted"
- Fixed: JPG Export not outputting a file
- Fixed: SVG exporting with individual line segments and consequently creating large output files.
- Fixed: Potential Memory Leaks with JavaFX
- Fixed: Blend Modes will render as expected again & also appear properly in exports.
- Note: Old user presets / presets will be broken, presets will need to transferred to the new json format (this won't happen again)

### [v1.0.3-beta](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.0.3-beta)
- Changed: The "Reset" / "Help" button has been swapped to help avoid accidental usage
- Fixed: JavaFX Native Bindings have been added, so Mac + Linux users should now be able to run the app as expected.

### [v1.0.2-beta](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.0.2-beta)
- Changed: DrawingBotV3 is now for Java 11+
- Removed: Dependency on Processing
- Fixed: Alpha channels being ignored by Path Finding Modules

### [v1.0.1-alpha](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.0.1-alpha)
- Added: User Configurable Presets which can be imported/exported and shared with other users
- Added: Default Path Finding Module Presets
  - inc. "Sketchy", "Glitchy Vertical, "Glitchy Horizontal" & "Messy Lines"
- Added: Default Pre-Processing Presets
  - inc. "Original Dirty Border"
- Added: Pre-Processing Settings, for adding/removing image filters
- Added: Large amounts of work on the API
- Changed: Improved application log output
- Changed: By default images will no longer have a border, use the pre-processing preset "Original Dirty Border" to replicate the original defaults.
- Changed: Combined the two Path Finding Control tabs into one smaller one
- Changed: Moved the Elapsed Time & Plotted Lines values to above the viewport
- Fixed: The application appearing to big on small screens.
- Fixed: The console log not being outputted properly
- Fixed: "Reset Plotting" button, this will now work more consistently and will try to force plotting to stop regardless of where the process is, the plotting will have to be restarted.
- Note: The "Pre-Processing" tab is still W.I.P.

### [v1.0.0-alpha](https://github.com/SonarSonic/DrawingBotV3/releases/tag/v1.0.0-alpha)
- The first public alpha!
