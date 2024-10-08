package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class TestListExercise {
    
    private ListExercise listex;
    private Exercise ex1;
    private Exercise ex2;

    @BeforeEach
    void setUp() {
        listex = new ListExercise();
        ex1 = new Exercise("Bicep Curls", 10);
        ex2 = new Exercise("Tricep Curls", 20);
    }

    @Test
    void testConstructor() {
        assertEquals(listex.getListExercise().size(), 0);
        assertTrue(listex.getListExercise().isEmpty());
        assertEquals(LocalDate.now(), listex.getDate());
    }

    @Test
    void testTotalCaloriesBurned() {
        listex.addExercise(ex1);
        listex.addExercise(ex2);

        assertEquals(listex.getTotalCaloriesBurnt(), 30);
    }

    @Test
    void testAddExerciseSingle() {
        listex.addExercise(ex1);

        assertEquals(listex.getListExercise().size(), 1);
        assertTrue(listex.getListExercise().contains(ex1));
        assertEquals(listex.getListExercise().get(0), ex1);
    }

    @Test
    void testAddExerciseMultiple() {
        listex.addExercise(ex1);

        assertEquals(listex.getListExercise().size(), 1);
        assertTrue(listex.getListExercise().contains(ex1));
        assertEquals(listex.getListExercise().get(0), ex1);

        listex.addExercise(ex2);

        assertEquals(listex.getListExercise().size(), 2);
        assertTrue(listex.getListExercise().contains(ex1));
        assertTrue(listex.getListExercise().contains(ex2));
        assertEquals(listex.getListExercise().get(0), ex1);
        assertEquals(listex.getListExercise().get(1), ex2);
    }

    @Test
    void testAddExerciseMultipleSame() {
        listex.addExercise(ex1);

        assertEquals(listex.getListExercise().size(), 1);
        assertTrue(listex.getListExercise().contains(ex1));
        assertEquals(listex.getListExercise().get(0), ex1);

        listex.addExercise(ex1);

        assertEquals(listex.getListExercise().size(), 2);
        assertTrue(listex.getListExercise().contains(ex1));
        assertEquals(listex.getListExercise().get(0), ex1);
        assertEquals(listex.getListExercise().get(1), ex1);
    }

    @Test
    void testRemoveExercise() {
        listex.addExercise(ex1);

        assertEquals(listex.getListExercise().size(), 1);
        assertTrue(listex.getListExercise().contains(ex1));
        assertEquals(listex.getListExercise().get(0), ex1);

        listex.removeExercise(ex1);

        assertEquals(listex.getListExercise().size(), 0);
        assertFalse(listex.getListExercise().contains(ex1));
        assertTrue(listex.getListExercise().isEmpty());
    }

    @Test
    void testRemoveExerciseMultiple() {
        listex.addExercise(ex1);
        listex.addExercise(ex2);

        assertEquals(listex.getListExercise().size(), 2);
        assertTrue(listex.getListExercise().contains(ex1));
        assertTrue(listex.getListExercise().contains(ex2));
        assertEquals(listex.getListExercise().get(0), ex1);
        assertEquals(listex.getListExercise().get(1), ex2);

        listex.removeExercise(ex1);

        assertEquals(listex.getListExercise().size(), 1);
        assertFalse(listex.getListExercise().contains(ex1));
        assertTrue(listex.getListExercise().contains(ex2));
        assertEquals(listex.getListExercise().get(0), ex2);
        assertFalse(listex.getListExercise().isEmpty());

        listex.removeExercise(ex2);

        assertEquals(listex.getListExercise().size(), 0);
        assertFalse(listex.getListExercise().contains(ex1));
        assertFalse(listex.getListExercise().contains(ex2));
        assertTrue(listex.getListExercise().isEmpty());
    }

    @Test
    void testSetDate() {
        listex.setDate();
        assertEquals(LocalDate.now(), listex.getDate());
    }

    @Test
    void testClearList() {
        listex.addExercise(ex1);
        listex.addExercise(ex2);

        assertEquals(listex.getListExercise().size(), 2);
        assertTrue(listex.getListExercise().contains(ex1));
        assertTrue(listex.getListExercise().contains(ex2));
        assertEquals(listex.getListExercise().get(0), ex1);
        assertEquals(listex.getListExercise().get(1), ex2);

        listex.clearList();
        assertEquals(listex.getListExercise().size(), 0);
        assertFalse(listex.getListExercise().contains(ex1));
        assertFalse(listex.getListExercise().contains(ex2));
        assertTrue(listex.getListExercise().isEmpty());
    }

    @Test
    void testSizeList() {
        listex.addExercise(ex1);
        listex.addExercise(ex2);

        assertEquals(listex.sizeList(), 2);

        listex.clearList();
        assertEquals(listex.sizeList(), 0);

        listex.addExercise(ex2);
        assertEquals(listex.sizeList(), 1);
    }
}
