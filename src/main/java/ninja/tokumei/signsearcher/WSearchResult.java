package ninja.tokumei.signsearcher;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WText;
import io.github.cottonmc.cotton.gui.widget.data.HorizontalAlignment;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import ninja.tokumei.signsearcher.mixin.SignBlockEntityMixin;

import java.util.ArrayList;

public class WSearchResult extends WGridPanel {
    static final int NUM_LINES = 4;

    private WText[] lines;
    private WText xPos;
    private WText yPos;
    private WText zPos;

    public WSearchResult() {
        super(9);

        this.lines = new WText[NUM_LINES];
        for (int i = 0; i < NUM_LINES; i++) {
            this.lines[i] = new WText(new LiteralText(""));
            this.lines[i].setHorizontalAlignment(HorizontalAlignment.CENTER);
            this.add(this.lines[i], 0, 1 + i, 12, 1);
        }

        this.xPos = new WText(new LiteralText(""));
        this.add(this.xPos, 12, 1, 8, 1);
        this.yPos = new WText(new LiteralText(""));
        this.add(this.yPos, 12, 2, 8, 1);
        this.zPos = new WText(new LiteralText(""));
        this.add(this.zPos, 12, 3, 8, 1);

        this.setSize(10 * 18, 3 * 18);
        this.setBackgroundPainter(BackgroundPainter.SLOT);
        this.layout();
    }

    public void setSign(SignBlockEntity sign) {
        Text[] srcText = ((SignBlockEntityMixin) sign).getText();

        for (int i = 0; i < NUM_LINES; i++) {
            if (i < srcText.length) {
                this.lines[i].setText(srcText[i]);
            } else {
                this.lines[i].setText(new LiteralText(""));
            }
        }
        this.xPos.setText(new LiteralText(String.format("X: %d", sign.getPos().getX())));
        this.yPos.setText(new LiteralText(String.format("Y: %d", sign.getPos().getY())));
        this.zPos.setText(new LiteralText(String.format("Z: %d", sign.getPos().getZ())));
    }
}
