getSpannableString("*Name", tv_name);

---

private void getSpannableString(String text, TextView view) {

    SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);

    spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.RED), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

    view.setText(spannableStringBuilder);

}
