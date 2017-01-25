/*
 * Decompiled with CFR 0_118.
 */
package swing;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class LineNumberTableRowHeader
extends JComponent {
    private final JTable table;
    private final JScrollPane scrollPane;

    public LineNumberTableRowHeader(JScrollPane jScrollPane, JTable table) {
        this.scrollPane = jScrollPane;
        this.table = table;
        this.table.getModel().addTableModelListener(tme -> {
            this.repaint();
        }
        );
        this.table.getSelectionModel().addListSelectionListener(lse -> {
            this.repaint();
        }
        );
        this.scrollPane.getVerticalScrollBar().addAdjustmentListener(ae -> {
            this.repaint();
        }
        );
        this.setPreferredSize(new Dimension(50, 100));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Point viewPosition = this.scrollPane.getViewport().getViewPosition();
        Dimension viewSize = this.scrollPane.getViewport().getViewSize();
        if (this.getHeight() < viewSize.height) {
            Dimension size = this.getPreferredSize();
            size.height = viewSize.height;
            this.setSize(size);
            this.setPreferredSize(size);
        }
        super.paintComponent(g);
        g.setColor(this.getBackground());
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        FontMetrics fm = g.getFontMetrics();
        for (int r = 0; r < this.table.getRowCount(); ++r) {
            Rectangle cellRect = this.table.getCellRect(r, 0, false);
            boolean rowSelected = this.table.isRowSelected(r);
            if (rowSelected) {
                g.setColor(this.table.getSelectionBackground());
                g.fillRect(0, cellRect.y, this.getWidth(), cellRect.height);
            }
            if (cellRect.y + cellRect.height - viewPosition.y < 0 || cellRect.y >= viewPosition.y + viewSize.height) continue;
            g.setColor(this.table.getGridColor());
            g.drawLine(0, cellRect.y + cellRect.height, this.getWidth(), cellRect.y + cellRect.height);
            g.setColor(rowSelected ? this.table.getSelectionForeground() : this.getForeground());
            String s = Integer.toString(r);
            g.drawString(s, this.getWidth() - fm.stringWidth(s) - 8, cellRect.y + cellRect.height - fm.getDescent());
        }
        if (this.table.getShowVerticalLines()) {
            g.setColor(this.table.getGridColor());
            g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
        }
    }
}
