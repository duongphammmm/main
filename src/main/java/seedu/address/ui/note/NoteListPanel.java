package seedu.address.ui.note;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.note.Note;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of note entries.
 */
public class NoteListPanel extends UiPart<Region> {
    private static final String FXML = "NoteListPanel.fxml";

    private final Logger logger = LogsCenter.getLogger(NoteListPanel.class);

    @FXML
    private ListView<Note> noteListView;

    public NoteListPanel(ObservableList<Note> notes) {
        super(FXML);
        noteListView.setItems(notes);
        noteListView.setCellFactory(listView -> new NoteListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code NoteEntry} using a {@code NoteCard}.
     */
    class NoteListViewCell extends ListCell<Note> {
        @Override
        protected void updateItem(Note note, boolean empty) {
            super.updateItem(note, empty);

            if (empty || note == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new NoteCard(note, note.getIndex() + 1).getRoot());
            }
        }
    }
}
