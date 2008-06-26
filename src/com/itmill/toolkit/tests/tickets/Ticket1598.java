package com.itmill.toolkit.tests.tickets;

import java.util.ArrayList;
import java.util.List;

import com.itmill.toolkit.Application;
import com.itmill.toolkit.terminal.ThemeResource;
import com.itmill.toolkit.ui.MenuBar;
import com.itmill.toolkit.ui.Window;
import com.itmill.toolkit.ui.MenuBar.Command;
import com.itmill.toolkit.ui.MenuBar.Item;

public class Ticket1598 extends Application {

    Window main = new Window("MenuBar test");

    final MenuBar menuBar = new MenuBar();

    public void init() {
        setMainWindow(main);
        setTheme("default");

        List itemList = new ArrayList();
        // Populate the menubar
        for (int i = 0; i < 5; i++) {
            itemList.add(menuBar.addItem(new String("Menu " + i), null, null));
        }

        Item first = (Item) itemList.get(0);

        for (int i = 0; i < 5; i++) {
            first.addItem(new String("Submenu item" + i), null, new Command() {

                public void menuSelected(Item selected) {
                    main.showNotification("Action " + selected.getText());
                }
            });
        }

        Item firstSecond = (Item) first.getChildren().get(1);

        for (int i = 0; i < 3; i++) {
            firstSecond.addItem(new String("Subsubmenu item" + i), null,
                    new Command() {

                        public void menuSelected(Item selected) {
                            main.showNotification("Action "
                                    + selected.getText());
                        }
                    });
        }

        Item second = (Item) menuBar.getItems().get(1);

        for (int i = 0; i < 5; i++) {
            second.addItem(new String("Second submenu item" + i), null,
                    new Command() {

                        public void menuSelected(Item selected) {
                            main.showNotification("Action "
                                    + selected.getText());
                        }
                    });
        }

        Item third = (Item) menuBar.getItems().get(2);
        third.setIcon(new ThemeResource("icons/16/document.png"));

        for (int i = 2; i <= 3; i++)
            ((Item) menuBar.getItems().get(i)).setCommand(new Command() {

                public void menuSelected(Item selectedItem) {
                    main.showNotification("Action " + selectedItem.getText());
                }
            });

        final Item fourth = (Item) menuBar.getItems().get(3);
        fourth.setText("Toggle animation");

        fourth.setCommand(new Command() {
            public void menuSelected(Item selected) {
                menuBar
                        .addItemBefore("No animation yet...", null, null,
                                fourth);
                // menuBar.setAnimation(!menuBar.hasAnimation());
            }
        });

        final Item last = (Item) menuBar.getItems().get(menuBar.getSize() - 1);
        last.setText("Remove me!");

        // A command for removing the selected menuitem
        Command removeCommand = new Command() {

            public void menuSelected(Item selected) {
                Item parent = selected.getParent();
                if (parent != null) {
                    parent.removeChild(selected);
                } else {
                    menuBar.removeItem(selected);
                }
            }
        };

        last.setCommand(removeCommand);

        main.addComponent(menuBar);

    }
}