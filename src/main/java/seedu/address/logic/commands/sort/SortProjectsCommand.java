package seedu.address.logic.commands.sort;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.SortCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.Project;

/**
 * Sorts Project items in the resume book.
 */
public class SortProjectsCommand extends SortCommand {
    private final Comparator<Project> sortComparator;
    private final String sortOrder;
    private final boolean reverse;

    public SortProjectsCommand(String sortOrder, boolean reverse) {
        this.sortOrder = sortOrder;
        this.reverse = reverse;
        Comparator<Project> baseComparator =
                sortOrder.equalsIgnoreCase("name")
                        ? Comparator.comparing(Project::getName)
                        : Comparator.comparing(Project::getTime);
        sortComparator = reverse ? baseComparator.reversed() : baseComparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortProjects(sortComparator);
        model.setProjectToDisplay();
        model.commitResumeBook();

        return new SortCommandResult(
                String.format(MESSAGE_SUCCESS, Project.class.getSimpleName()), model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SortProjectsCommand
                && sortOrder.equals(((SortProjectsCommand) other).sortOrder)
                && reverse == ((SortProjectsCommand) other).reverse);
    }
}
