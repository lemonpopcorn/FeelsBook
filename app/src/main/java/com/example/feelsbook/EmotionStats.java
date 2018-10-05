package com.example.feelsbook;

/*

    Purpose: hold the number counts of each emotion

    Rationale: To have a single class that manages the
    statistics part of the app allows convenient data store
    using Gson.

 */

public class EmotionStats {

    private Integer loveCount = 0;
    private Integer angerCount = 0;
    private Integer fearCount = 0;
    private Integer sadnessCount = 0;
    private Integer surpriseCount = 0;
    private Integer joyCount = 0;

    public Integer getLoveCount(){
        return this.loveCount;
    }

    public void incrementLove(){
        this.loveCount++;
    }

    public void decrementLove(){
        this.loveCount--;
    }

    public Integer getAngerCount(){
        return this.angerCount;
    }

    public void incrementAnger(){
        this.angerCount++;
    }

    public Integer getFearCount(){
        return this.fearCount;
    }

    public void incrementFear(){
        this.fearCount++;
    }

    public Integer getSadnessCount(){
        return this.sadnessCount;
    }

    public void incrementSadness(){
        this.sadnessCount++;
    }

    public Integer getSurpriseCount(){
        return this.surpriseCount;
    }

    public void incrementSurprise(){
        this.surpriseCount++;
    }

    public Integer getJoyCount(){
        return this.joyCount;
    }

    public void incrementJoy(){
        this.joyCount++;
    }

    public void decrementAnger() {
        this.angerCount--;
    }

    public void decrementFear() {
        this.fearCount--;
    }

    public void decrementJoy() {
        this.joyCount--;
    }

    public void decrementSurprise() {
        this.surpriseCount--;
    }

    public void decrementSadness() {
        this.sadnessCount--;
    }
}
