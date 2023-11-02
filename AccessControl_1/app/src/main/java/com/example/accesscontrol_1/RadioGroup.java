RadioGroup radioGroup = findViewById(R.id.radioGroup);
RadioButton radioButtonYes = findViewById(R.id.radioButtonYes);
RadioButton radioButtonNo = findViewById(R.id.radioButtonNo);

radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
    if (checkedId == R.id.radioButtonYes) {
        // El usuario seleccionó "Sí".
        String selectedOption = "Sí";
    } else if (checkedId == R.id.radioButtonNo) {
        // El usuario seleccionó "No".
        String selectedOption = "No";
    }
});