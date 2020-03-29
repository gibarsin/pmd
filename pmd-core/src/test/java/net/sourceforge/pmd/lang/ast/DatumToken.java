/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */

package net.sourceforge.pmd.lang.ast;

public final class DatumToken implements GenericToken {
    private int beginLine;
    private int endLine;
    private int beginColumn;
    private int endColumn;
    private String image;

    private GenericToken next;
    private GenericToken previousComment;

    /* default */ DatumToken() {
        image = "";
        next = null;
    }

    @Override
    public int getBeginLine() {
        return beginLine;
    }

    @Override
    public void setBeginLine(int beginLine) {
        this.beginLine = beginLine;
    }

    @Override
    public int getEndLine() {
        return endLine;
    }

    @Override
    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    @Override
    public int getBeginColumn() {
        return beginColumn;
    }

    @Override
    public void setBeginColumn(int beginColumn) {
        this.beginColumn = beginColumn;
    }

    @Override
    public int getEndColumn() {
        return endColumn;
    }

    @Override
    public void setEndColumn(int endColumn) {
        this.endColumn = endColumn;
    }

    @Override
    public String getImage() {
        return image;
    }

    @Override
    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public GenericToken getNext() {
        return next;
    }

    @Override
    public void setNext(GenericToken next) {
        this.next = next;
    }

    @Override
    public GenericToken getPreviousComment() {
        return previousComment;
    }
}
