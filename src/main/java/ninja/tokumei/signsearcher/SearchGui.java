package ninja.tokumei.signsearcher;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WBox;
import io.github.cottonmc.cotton.gui.widget.WListPanel;
import io.github.cottonmc.cotton.gui.widget.WTextField;
import io.github.cottonmc.cotton.gui.widget.data.Axis;
import net.minecraft.block.entity.SignBlockEntity;

import java.util.ArrayList;

public class SearchGui extends LightweightGuiDescription {
    ArrayList<SignBlockEntity> resultsList;

    public SearchGui() {
        resultsList = new ArrayList<>();

        WBox root = new WBox(Axis.VERTICAL);
        setRootPanel(root);

        WTextField searchBar = new WTextField();
        root.add(searchBar);

        WListPanel<SignBlockEntity, ResultPanel> searchResults = new WListPanel<>(
                resultsList, ResultPanel::new, (sign, panel) -> panel.setSign(sign));
        root.add(searchResults);

        root.validate(this);
    }
}
