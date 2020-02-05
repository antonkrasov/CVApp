package com.antonkrasov.cvapp

import com.antonkrasov.cvapp.data.model.CV
import com.antonkrasov.cvapp.data.model.DataItem
import com.antonkrasov.cvapp.data.model.Details
import com.antonkrasov.cvapp.data.model.Section

val fakeCV : CV = CV(
    title = "Test Title",
    subtitle = "Test Subtitle",
    description = "Test Description",
    details = Details(
        location = "Test Location",
        phone = "Test Phone",
        email = "Test Email"
    ),
    links = arrayOf("Test Link 1", "Test Link 2", "Test Link 3"),
    skills = arrayOf("Test Skill 1", "Test Skill 2", "Test Skill 3"),
    sections = arrayOf(
        Section(
            title = "Test Section",
            items = arrayOf(
                DataItem(
                    title = "Test Data Item Title 1",
                    description = "Test Data Item Description 1",
                    startDate = "Test Data Item Start Date 1",
                    endDate = "Test Data Item Start Date 2"
                ),
                DataItem(
                    title = "Test Data Item Title 1",
                    description = "Test Data Item Description 1",
                    startDate = "Test Data Item Start Date 1",
                    endDate = "Test Data Item Start Date 2"
                )
            ),
            icon = "Test icon"
        )
    ),
    lastUpdate = 100500
)