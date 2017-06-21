# MaterialPillsBox Widget

<p align="center">
    <img src="Screenshots/ic_pillsbox_launcher-web.png" alt="icon" width="30%"/>
</p>

MaterialPillsBox is a widget that allows you to implement a list of any objects
and show them like a pills with a little material style.

# Usage

```xml
<pe.orbis.materialpillsbox.MaterialPillsBox
            android:id="@+id/mtbArea"
            android:layout_width="match_parent"
            android:layout_height="40dp"
            app:closeIcon="@drawable/ic_edit_white_24dp"
            app:closeIconPadding="@dimen/default_close_icon_margin"
            app:maxPills="20"
            app:pillSelectedBackground="@drawable/shape_button_pill_red"
            app:pillSelectionMode="multi"
            app:showCloseIcon="true"
            app:pillTextColor="@color/md_amber_500"
            app:pillMarginBottom="10dp"/>
```



# Setup
For using the MaterialPillBox widget in your Activity, just implement the initFirstSetup()`
  
# The PillEntity class                          
Any of your entities have to extend the `PillEntity` class(this is the baseEntity). This class contains 
3 basic attributes which are: 

* id(int): Unique Id for your pill
* message(String): Message to show in the pill
* pressed(boolean): State of the pill.

# Main Properties

##### maxPills
    The maximum number of pills 
##### pillBackground
    The pill's brackground so you can add any drawable to your pills
##### pillSelectedBackground
    The pill's brackground when is selected so you can add any drawable to your pills
##### showCloseIcon
    If you wanna show the close icon of the pill
##### closeIcon
    Set any icon for the pill
##### closeIconPadding
    The separation between pill's message and the close icon
#### pillSelectionMode
    We have two selection mode, multiselection and none selection



















