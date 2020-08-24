package ninja.tokumei.signsearcher;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WBox;
import io.github.cottonmc.cotton.gui.widget.WListPanel;
import io.github.cottonmc.cotton.gui.widget.WTextField;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import net.minecraft.block.entity.SignBlockEntity;

import java.util.ArrayList;

public class SearchGui extends LightweightGuiDescription {
    public ArrayList<SignBlockEntity> resultsList;

    public SearchGui() {
        resultsList = new ArrayList<>();

        WBox root = new WBox(Axis.VERTICAL);
        setRootPanel(root);

        WTextField searchBar = new WTextField();
        root.add(searchBar, 11 * 18, 1 * 18);

        WListPanel<SignBlockEntity, WSearchResult> searchResults = new WListPanel<>(
                resultsList, WSearchResult::new, (sign, panel) -> panel.setSign(sign));
        searchResults.setListItemHeight(3 * 18);
        root.add(searchResults, 11 * 18, 12 * 18);

        root.validate(this);
    }
}
