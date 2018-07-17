import com.google.inject.Inject;

class TextEditor {
    private SpellChecker spellChecker;

    // to make it explicit that the constructor is invoked by Guice, we add the @Inject annotation
    @Inject
    public TextEditor(SpellChecker spellChecker) {
        this.spellChecker = spellChecker;
    }

    public void makeSpellCheck() {
        spellChecker.checkSpelling();
    }
}
