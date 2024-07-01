package com.bank.enums;

public enum Level {

    SEVERE_HYPOGLYCEMIA(Double.NEGATIVE_INFINITY, 54, "Your glycemic level is dangerously low. Consume fast-acting carbohydrates immediately, and consult a healthcare professional as soon as possible."),
    MODERATE_HYPOGLYCEMIA(54, 70, "Your glycemic level is low. Consume fast-acting carbohydrates and monitor your glycemic level regularly."),
    LOW_NORMAL(70, 90, "Your glycemic level is normal but low. Make sure to eat balanced meals and monitor your glycemic level."),
    NORMAL(90, 110, "Your glycemic level is normal. Keep maintaining a healthy lifestyle."),
    HIGH_NORMAL(110, 140, "Your glycemic level is normal but slightly high. Watch your diet and exercise regularly."),
    MODERATE_HYPERGLYCEMIA(140, 180, "Your glycemic level is slightly elevated. Reevaluate your diet and exercise habits."),
    SEVERE_HYPERGLYCEMIA(180, Double.POSITIVE_INFINITY, "Your glycemic level is high. Consult your healthcare provider to evaluate the cause and adjust your treatment.");

    private final double minLevel;
    private final double maxLevel;
    private final String defaultConseil;

    Level(double minLevel, double maxLevel, String defaultConseil) {
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.defaultConseil = defaultConseil;
    }

    public double getMinLevel() {
        return minLevel;
    }

    public double getMaxLevel() {
        return maxLevel;
    }

    public String getDefaultConseil() {
        return defaultConseil;
    }

    public static Level fromValue(double value) {
        for (Level level : Level.values()) {
            if (value >= level.minLevel && value < level.maxLevel) {
                return level;
            }
        }
        throw new IllegalArgumentException("Invalid glycemic level: " + value);
    }
}
