package com.antonkrasov.cvapp.ui.main.cvadapter

import com.antonkrasov.cvapp.fakeCV
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class SectionsBuilderTest{


    private val sectionsBuilder : SectionsBuilder = SectionsBuilder()
    private val sections = sectionsBuilder.build(fakeCV)

    @Test
    fun `Correct Number Of Sections`() {
        assertEquals(sections.size, 6)
    }

    @Test
    fun `Correct Details Section`() {
        val detailsSection = sections.find { it is DetailsSection } as DetailsSection

        assertEquals(detailsSection.title, fakeCV.title)
        assertEquals(detailsSection.subtitle, fakeCV.subtitle)
        assertEquals(detailsSection.description, fakeCV.description)
        assertEquals(detailsSection.details, fakeCV.details)
    }

    @Test
    fun `Correct Links Section`() {
        val linksSection = sections.find { it is LinksSection } as LinksSection

        assertNotNull(linksSection)
    }

    @Test
    fun `Correct Skills Section`() {
        val skillsSection = sections.find { it is SkillsSection } as SkillsSection

        assertNotNull(skillsSection)
        assertEquals(skillsSection.skills!!.size, fakeCV.skills!!.size)
    }

    @Test
    fun `Correct Header Section`() {
        val headerSection = sections.find { it is HeaderSection } as HeaderSection

        assertEquals(headerSection.heading, fakeCV.sections!![0].title)
        assertEquals(headerSection.iconID, fakeCV.sections!![0].icon)
    }

    @Test
    fun `Correct Data Item Count`() {
        val dataItemsCount = sections.count { it is DataItemSection }

        assertEquals(dataItemsCount, 2)
    }

    @Test
    fun `Correct Data Item Section`() {
        val firstDataItem = sections.find { it is DataItemSection } as DataItemSection
        assertEquals(firstDataItem.dataItem, fakeCV.sections!![0].items!![0])

        val lastDataItem = sections.findLast { it is DataItemSection } as DataItemSection
        assertEquals(lastDataItem.dataItem, fakeCV.sections!![0].items!![1])
    }



}