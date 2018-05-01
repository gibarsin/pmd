package net.sourceforge.pmd.util;

import net.sourceforge.pmd.lang.ast.AbstractNode;
import net.sourceforge.pmd.lang.ast.GenericToken;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static net.sourceforge.pmd.util.DiffMatchPatch.*;

/**
 * Utility for converting an AST to text.
 */
public enum SourceCodeWriter {
    ;

    public static void saveSyncedSourceCodeToFile(final String fileName, final Charset charset, final AbstractNode root) throws IOException {
        final Path temporaryPath = Files.createTempFile("pmd-", ".tmp");
        // TODO root.syncTokens()
        try (Writer writer = Files.newBufferedWriter(temporaryPath, charset)) {
            writeSourceCode(writer, root);
        }
        final Path filePath = Paths.get(fileName);
        Files.move(temporaryPath, filePath, REPLACE_EXISTING); // Platform-independent
    }

    public static String getASTSourceCodeAsString(final AbstractNode root) throws IOException {
        final StringBuilder sourceCode = new StringBuilder();
        // TODO root.syncTokens()
        writeSourceCode(sourceCode, root);
        return sourceCode.toString();
    }

    public static void applyPatchesToOriginalSourceCodeFile(final String fileName, final Charset charset, final List<Patch> patches) throws IOException {
        final String sourceCode = getSourceCodeFileAsString(fileName, charset);
        final Object[] patchedSourceCodeAndResults = new DiffMatchPatch().applyPatches(patches, sourceCode);
        final String patchedSourceCode = (String) patchedSourceCodeAndResults[0];

        final Path temporaryPath = Files.createTempFile("pmd-", ".tmp");
        try (Writer writer = Files.newBufferedWriter(temporaryPath, charset)) {
            writer.write(patchedSourceCode);
        }
        final Path filePath = Paths.get(fileName);
        Files.move(temporaryPath, filePath, REPLACE_EXISTING); // Platform-independent
    }

    public static String getSourceCodeFileAsString(final String fileName, final Charset charset) throws IOException {
        final byte[] sourceCodeBytes = Files.readAllBytes(Paths.get(fileName));
        return new String(sourceCodeBytes, charset);
    }

    private static void writeSourceCode(final Appendable sourceCode, final AbstractNode root) throws IOException {
        if (root.jjtGetFirstToken() == null) {
            return;
        }

        final GenericToken lastToken = root.jjtGetLastToken();
        GenericToken currToken = root.jjtGetFirstToken();
        do {
            writeSpecialTokens(sourceCode, currToken.getPreviousComment());
            sourceCode.append(currToken.getImage());
            currToken = currToken.getNext();
        } while (currToken != lastToken);
    }

    private static void writeSpecialTokens(final Appendable sourceCode, final GenericToken specialToken) throws IOException {
        final Deque<GenericToken> specialTokens = new LinkedList<>();

        GenericToken currSpecialToken = specialToken;
        while (currSpecialToken != null) {
            specialTokens.push(currSpecialToken);
            currSpecialToken = currSpecialToken.getPreviousComment();
        }

        while (!specialTokens.isEmpty()) {
            sourceCode.append(specialTokens.pop().getImage());
        }
    }
}
