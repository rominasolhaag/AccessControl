Button backButton = findViewById(R.id.backButton);
backButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        finish(); // Cierra la actividad actual y vuelve a la anterior.
    }
});
