package com.arjun.android.familink.views

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View

class RegisterLoginViewPager(context: Context, attributeSet: AttributeSet) :
        ViewPager(context, attributeSet){
    var direction: SwipeDirection = SwipeDirection.down // The user can only swipe down
    var initY: Float = 0f // See isSwipeRegionAllowed
    var allowed: Boolean = false // A boolean for whether the user's swipe is valid

    init {
        setPageTransformer(true, VerticalPageTransformer()) // Make the pager vertically scroll
    }

    private fun swapXY(event: MotionEvent?): MotionEvent {
        // When swapping the coords the ratio of coord to axis length must be compensated for
        val xSwap = (event?.y!! / height) * width
        val ySwap = (event.x / width) * height

        event.setLocation(xSwap, ySwap) // Manually set the location of the TouchEvent to the new
                                        //  coords
        return event
    }

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        val intercepted: Boolean = super.onInterceptTouchEvent(swapXY(event)) // Intercept touch
        swapXY(event) // Swap coords
        return intercepted // Return new event
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d("TOUCHEVENT", "x: ${event?.x}, y: ${event?.y}\n" +
                "width: $width, height: $height")
        // If the swipe direction is down and it originates in the specified region
        if (isSwipeDirectionAllowed(event) && isSwipeRegionAllowed(event))
            return super.onTouchEvent(swapXY(event)) // Return event with swapped coords
        return false
    }

    fun isSwipeDirectionAllowed(event: MotionEvent?): Boolean {
        if (direction == SwipeDirection.all) return true // Auto-true if set to all
        if (direction == SwipeDirection.none) return false // Auto-false if set to none

        when (event?.action!! and ACTION_MASK) {
            ACTION_DOWN -> initY = event.y // Get the start y-coord of the touch
            ACTION_MOVE -> {
                val dy: Float = event.y - initY // Difference between initY and movement coord
                // If dy is positive (moving up) but direction is down then return false
                if (dy > 0 && direction == SwipeDirection.down) return false
                // If dy is negative (moving down) but direction is up then return false
                else if (dy < 0 && direction == SwipeDirection.up) return false
            }
        }

        return true // Necessary but probably unreachable catch-all
    }

    fun isSwipeRegionAllowed(event: MotionEvent?): Boolean {
        // Allowed only if swipe starts in the first 10% of screen
        if (event?.action!! and ACTION_MASK == ACTION_DOWN) allowed = event.y >= 0.9*height

        return allowed
    }

    private inner class VerticalPageTransformer : ViewPager.PageTransformer {
        override fun transformPage(view: View?, position: Float) {
            // position is in ]-∞,1[
            if (position < -1) view?.alpha = 0f // Page is invisible
            // [-1,1]
            else if (position <= 1) {
                view?.alpha = 1f // Page is visible
                // Reverse the default horizontal translation
                view?.translationX = view?.width!! *  -position
                // Translate the page vertically
                view.translationY = view.height * position
            } else view?.alpha = 0f // position is in ]1,∞[ | Page is invisible
        }
    }

    // Just a little enum for swipe directions
    enum class SwipeDirection {
        all, up, down, none
    }
}