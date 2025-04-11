package com.server.myapp.web.rest;

import static com.server.myapp.domain.StaffAsserts.*;
import static com.server.myapp.web.rest.TestUtil.createUpdateProxyForBean;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.myapp.IntegrationTest;
import com.server.myapp.domain.Staff;
import com.server.myapp.repository.StaffRepository;
import com.server.myapp.repository.UserRepository;
import com.server.myapp.service.dto.StaffDTO;
import com.server.myapp.service.mapper.StaffMapper;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link StaffResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class StaffResourceIT {

    private static final String DEFAULT_STAFF_ID = "AAAAAAAAAA";
    private static final String UPDATED_STAFF_ID = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION = "AAAAAAAAAA";
    private static final String UPDATED_POSITION = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_EDUCATION = "AAAAAAAAAA";
    private static final String UPDATED_EDUCATION = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_AVATAR_URL = "AAAAAAAAAA";
    private static final String UPDATED_AVATAR_URL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/staff";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restStaffMockMvc;

    private Staff staff;

    private Staff insertedStaff;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Staff createEntity() {
        return new Staff()
            .staffId(DEFAULT_STAFF_ID)
            .fullName(DEFAULT_FULL_NAME)
            .position(DEFAULT_POSITION)
            .phone(DEFAULT_PHONE)
            .address(DEFAULT_ADDRESS)
            .education(DEFAULT_EDUCATION)
            .email(DEFAULT_EMAIL)
            .avatarUrl(DEFAULT_AVATAR_URL);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Staff createUpdatedEntity() {
        return new Staff()
            .staffId(UPDATED_STAFF_ID)
            .fullName(UPDATED_FULL_NAME)
            .position(UPDATED_POSITION)
            .phone(UPDATED_PHONE)
            .address(UPDATED_ADDRESS)
            .education(UPDATED_EDUCATION)
            .email(UPDATED_EMAIL)
            .avatarUrl(UPDATED_AVATAR_URL);
    }

    @BeforeEach
    public void initTest() {
        staff = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedStaff != null) {
            staffRepository.delete(insertedStaff);
            insertedStaff = null;
        }
    }

    @Test
    @Transactional
    void createStaff() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Staff
        StaffDTO staffDTO = staffMapper.toDto(staff);
        var returnedStaffDTO = om.readValue(
            restStaffMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(staffDTO)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            StaffDTO.class
        );

        // Validate the Staff in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        var returnedStaff = staffMapper.toEntity(returnedStaffDTO);
        assertStaffUpdatableFieldsEquals(returnedStaff, getPersistedStaff(returnedStaff));

        insertedStaff = returnedStaff;
    }

    @Test
    @Transactional
    void createStaffWithExistingId() throws Exception {
        // Create the Staff with an existing ID
        staff.setId(1L);
        StaffDTO staffDTO = staffMapper.toDto(staff);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restStaffMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(staffDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Staff in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkStaffIdIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        staff.setStaffId(null);

        // Create the Staff, which fails.
        StaffDTO staffDTO = staffMapper.toDto(staff);

        restStaffMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(staffDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkFullNameIsRequired() throws Exception {
        long databaseSizeBeforeTest = getRepositoryCount();
        // set the field null
        staff.setFullName(null);

        // Create the Staff, which fails.
        StaffDTO staffDTO = staffMapper.toDto(staff);

        restStaffMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(staffDTO)))
            .andExpect(status().isBadRequest());

        assertSameRepositoryCount(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllStaff() throws Exception {
        // Initialize the database
        insertedStaff = staffRepository.saveAndFlush(staff);

        // Get all the staffList
        restStaffMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(staff.getId().intValue())))
            .andExpect(jsonPath("$.[*].staffId").value(hasItem(DEFAULT_STAFF_ID)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].position").value(hasItem(DEFAULT_POSITION)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE)))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS)))
            .andExpect(jsonPath("$.[*].education").value(hasItem(DEFAULT_EDUCATION)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].avatarUrl").value(hasItem(DEFAULT_AVATAR_URL)));
    }

    @Test
    @Transactional
    void getStaff() throws Exception {
        // Initialize the database
        insertedStaff = staffRepository.saveAndFlush(staff);

        // Get the staff
        restStaffMockMvc
            .perform(get(ENTITY_API_URL_ID, staff.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(staff.getId().intValue()))
            .andExpect(jsonPath("$.staffId").value(DEFAULT_STAFF_ID))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME))
            .andExpect(jsonPath("$.position").value(DEFAULT_POSITION))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS))
            .andExpect(jsonPath("$.education").value(DEFAULT_EDUCATION))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.avatarUrl").value(DEFAULT_AVATAR_URL));
    }

    @Test
    @Transactional
    void getNonExistingStaff() throws Exception {
        // Get the staff
        restStaffMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingStaff() throws Exception {
        // Initialize the database
        insertedStaff = staffRepository.saveAndFlush(staff);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the staff
        Staff updatedStaff = staffRepository.findById(staff.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedStaff are not directly saved in db
        em.detach(updatedStaff);
        updatedStaff
            .staffId(UPDATED_STAFF_ID)
            .fullName(UPDATED_FULL_NAME)
            .position(UPDATED_POSITION)
            .phone(UPDATED_PHONE)
            .address(UPDATED_ADDRESS)
            .education(UPDATED_EDUCATION)
            .email(UPDATED_EMAIL)
            .avatarUrl(UPDATED_AVATAR_URL);
        StaffDTO staffDTO = staffMapper.toDto(updatedStaff);

        restStaffMockMvc
            .perform(
                put(ENTITY_API_URL_ID, staffDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(staffDTO))
            )
            .andExpect(status().isOk());

        // Validate the Staff in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedStaffToMatchAllProperties(updatedStaff);
    }

    @Test
    @Transactional
    void putNonExistingStaff() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        staff.setId(longCount.incrementAndGet());

        // Create the Staff
        StaffDTO staffDTO = staffMapper.toDto(staff);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStaffMockMvc
            .perform(
                put(ENTITY_API_URL_ID, staffDTO.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(staffDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Staff in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchStaff() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        staff.setId(longCount.incrementAndGet());

        // Create the Staff
        StaffDTO staffDTO = staffMapper.toDto(staff);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStaffMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(staffDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Staff in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamStaff() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        staff.setId(longCount.incrementAndGet());

        // Create the Staff
        StaffDTO staffDTO = staffMapper.toDto(staff);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStaffMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(staffDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Staff in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateStaffWithPatch() throws Exception {
        // Initialize the database
        insertedStaff = staffRepository.saveAndFlush(staff);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the staff using partial update
        Staff partialUpdatedStaff = new Staff();
        partialUpdatedStaff.setId(staff.getId());

        partialUpdatedStaff.phone(UPDATED_PHONE).email(UPDATED_EMAIL).avatarUrl(UPDATED_AVATAR_URL);

        restStaffMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStaff.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedStaff))
            )
            .andExpect(status().isOk());

        // Validate the Staff in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertStaffUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedStaff, staff), getPersistedStaff(staff));
    }

    @Test
    @Transactional
    void fullUpdateStaffWithPatch() throws Exception {
        // Initialize the database
        insertedStaff = staffRepository.saveAndFlush(staff);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the staff using partial update
        Staff partialUpdatedStaff = new Staff();
        partialUpdatedStaff.setId(staff.getId());

        partialUpdatedStaff
            .staffId(UPDATED_STAFF_ID)
            .fullName(UPDATED_FULL_NAME)
            .position(UPDATED_POSITION)
            .phone(UPDATED_PHONE)
            .address(UPDATED_ADDRESS)
            .education(UPDATED_EDUCATION)
            .email(UPDATED_EMAIL)
            .avatarUrl(UPDATED_AVATAR_URL);

        restStaffMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedStaff.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedStaff))
            )
            .andExpect(status().isOk());

        // Validate the Staff in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertStaffUpdatableFieldsEquals(partialUpdatedStaff, getPersistedStaff(partialUpdatedStaff));
    }

    @Test
    @Transactional
    void patchNonExistingStaff() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        staff.setId(longCount.incrementAndGet());

        // Create the Staff
        StaffDTO staffDTO = staffMapper.toDto(staff);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStaffMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, staffDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(staffDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Staff in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchStaff() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        staff.setId(longCount.incrementAndGet());

        // Create the Staff
        StaffDTO staffDTO = staffMapper.toDto(staff);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStaffMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(staffDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Staff in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamStaff() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        staff.setId(longCount.incrementAndGet());

        // Create the Staff
        StaffDTO staffDTO = staffMapper.toDto(staff);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restStaffMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(staffDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Staff in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteStaff() throws Exception {
        // Initialize the database
        insertedStaff = staffRepository.saveAndFlush(staff);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the staff
        restStaffMockMvc
            .perform(delete(ENTITY_API_URL_ID, staff.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return staffRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Staff getPersistedStaff(Staff staff) {
        return staffRepository.findById(staff.getId()).orElseThrow();
    }

    protected void assertPersistedStaffToMatchAllProperties(Staff expectedStaff) {
        assertStaffAllPropertiesEquals(expectedStaff, getPersistedStaff(expectedStaff));
    }

    protected void assertPersistedStaffToMatchUpdatableProperties(Staff expectedStaff) {
        assertStaffAllUpdatablePropertiesEquals(expectedStaff, getPersistedStaff(expectedStaff));
    }
}
