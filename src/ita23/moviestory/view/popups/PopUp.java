package ita23.moviestory.view.popups;

import javax.swing.*;

/**
 * This interface is used to represent a single PopUp, which is
 *  shown on the GUI using the {@code GuiManager}.</p>
 * This interface also works as a listener-interface for the
 *  observer-pattern to notify the {@code PopUp} when it was
 *  closed by the user.
 * @author Lukas Knuth
 * @version 1.0
 */
public interface PopUp {

    /**
     * The possible reasons why this {@code PopUp} was closed.
     */
    public enum CloseReason {
        USER_CLOSED, OVERLAPPED
    }

    /**
     * Is called, when the popup is normally opened.</p>
     *  The method can return {@code false} to indicate, that
     *  it can not be opened at this point. This can be due to
     *  the fact that some options are currently deactivated.
     * @return {@code false} if you want to abort the PopUp
     *  being opened, {@code true} otherwise.
     */
    public boolean open();

    /**
     * This method will be called, when the PopUp is opened
     *  to return some kind of result.</p>
     * This can be used to get input from a user.
     * @return the result that should be returned by the
     *  PopUp.
     */
    public Object openForResult();

    /**
     * Returns the view of this PopUp as a {@code JComponent}.</p>
     * Normally, this will be everything that will be shown in the
     *  popup.
     * @return the view to show in the popup.
     */
    public JComponent getView();

    /**
     * Get the title for the PopUp window.</p>
     * This should describe the content of thw window and also
     *  shouldn't be too long.
     * @return the title for the window.
     */
    public String getTitle();

    /**
     * This method is called when the popup was closed by the user or
     *  another popup came on front, causing this one to be closed.</p>
     * The reason why this popup was closed can be obtained by using
     *  the passed {@code state}-argument.
     * @param state the state of this {@code PopUp}.
     */
    public void popupClosed(CloseReason state);
}
