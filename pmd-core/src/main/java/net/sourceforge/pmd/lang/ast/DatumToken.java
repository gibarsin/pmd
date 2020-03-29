package net.sourceforge.pmd.lang.ast;

public final class DatumToken implements GenericToken {
    private int beginLine;
    private int endLine;
    private int beginColumn;
    private int endColumn;
    private String image;
    private GenericToken next;
    private GenericToken previousComment;

    private DatumToken(int beginLine, int endLine, int beginColumn, int endColumn, String image,
                       GenericToken next, GenericToken previousComment) {
        this.beginLine = beginLine;
        this.endLine = endLine;
        this.beginColumn = beginColumn;
        this.endColumn = endColumn;
        this.image = image;
        this.next = next;
        this.previousComment = previousComment;
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

    public static final class Builder {
        private int beginLine;
        private int endLine;
        private int beginColumn;
        private int endColumn;
        private String image;
        private GenericToken next;
        private GenericToken previousComment;

        public Builder withBeginLine(int beginLine) {
            this.beginLine = beginLine;
            return this;
        }

        public Builder withEndLine(int endLine) {
            this.endLine = endLine;
            return this;
        }

        public Builder withBeginColumn(int beginColumn) {
            this.beginColumn = beginColumn;
            return this;
        }

        public Builder withEndColumn(int endColumn) {
            this.endColumn = endColumn;
            return this;
        }

        public Builder withImage(String image) {
            this.image = image;
            return this;
        }

        public Builder withNext(GenericToken next) {
            this.next = next;
            return this;
        }

        public Builder withPreviousComment(GenericToken previousComment) {
            this.previousComment = previousComment;
            return this;
        }

        public DatumToken build() {
            return new DatumToken(beginLine, endLine, beginColumn, endColumn, image, next, previousComment);
        }
    }
}
