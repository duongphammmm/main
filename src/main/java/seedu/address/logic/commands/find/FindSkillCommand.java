package seedu.address.logic.commands.find;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.logic.commands.results.FindCommandResult;
import seedu.address.model.Model;
import seedu.address.model.item.field.NameContainsKeywordsPredicate;

/**
 * Finds {@code Skill} items in the resume book whose name contains the keyword.
 * Keyword matching is case-insensitive.
 */
public class FindSkillCommand extends FindCommand {
    public FindSkillCommand(NameContainsKeywordsPredicate predicate) {
        super(predicate);
    }

    /**
     * Finds skills to model according to {@code predicate}.
     *
     * @param model {@code Model} which command will search filter.
     * @return      {@code CommandResult} that describes changes made when command execute runs successfully.
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setSkillToDisplay();
        model.updateFilteredItemList(predicate);
        return new FindCommandResult("",
                String.format(Messages.MESSAGE_ITEMS_LISTED, model.getFilteredItemList().size(), "Skills"),
                model.getDisplayType());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindSkillCommand // instanceof handles nulls
                && predicate.equals(((FindSkillCommand) other).predicate)); // state check
    }
}
