# GitHub Repository Creation - Manual Test Cases

## Test Case 1: Create Public Repository with Basic Settings

**Test Steps**:
1. Navigate to GitHub home page
2. Click "New" button or "+" icon in top navigation
3. Select "New repository"
4. Enter repository name (e.g., "test-public-repo")
5. Leave description empty
6. Ensure "Public" option is selected
7. Leave "Initialize this repository with a README" unchecked
8. Click "Create repository" button

**Expected Results**:
- Repository is created successfully
- User is redirected to repository page
- Repository is publicly accessible
- Repository appears in user's repository list
- Repository URL follows format: github.com/username/repository-name

---

## Test Case 2: Create Private Repository

**Test Steps**:
1. Navigate to new repository creation page
2. Enter repository name (e.g., "test-private-repo")
3. Add description: "This is a private test repository"
4. Select "Private" option
5. Check "Initialize this repository with a README"
6. Click "Create repository" button

**Expected Results**:
- Private repository is created successfully
- Repository page shows "Private" indicator
- README.md file is created
- Only the owner can access the repository

---

## Test Case 3: Create Repository with All Options

**Test Steps**:
1. Navigate to new repository creation page
2. Enter repository name: "full-featured-repo"
3. Add description: "Repository with all features enabled"
4. Select "Public"
5. Check "Add a README file"
6. Select .gitignore template (e.g., "Node" or "Python")
7. Choose a license (e.g., "MIT License")
8. Click "Create repository" button

**Expected Results**:
- Repository created with all specified options
- README.md file is present
- .gitignore file is created with selected template
- LICENSE file is added with chosen license
- Repository is properly initialized

---

## Test Case 4: Repository Name Validation

**Test Steps**:
1. Navigate to new repository creation page
2. Test various invalid names:
   - Empty name
   - Names with spaces: "my repo"
   - Names with special characters: "repo@#$"
   - Names starting with hyphen: "-repo"
   - Names ending with hyphen: "repo-"
   - Very long names (over character limit)
3. Observe validation messages

**Expected Results**:
- Invalid names show appropriate error messages
- "Create repository" button is disabled for invalid names
- Valid names enable the button
- Real-time validation feedback is provided

---

## Test Case 5: Duplicate Repository Name

**Test Steps**:
1. Create a repository with name "duplicate-test"
2. Attempt to create another repository with the same name "duplicate-test"
3. Click "Create repository"

**Expected Results**:
- Error message displayed: "Repository name already exists"
- Repository creation is prevented
- User remains on creation form
- Suggested alternative names may be provided

---

## Test Case 6: Repository Description Validation

**Test Steps**:
1. Navigate to new repository creation page
2. Enter valid repository name
3. Test description field:
   - Very long description (test character limits)
   - Description with special characters
   - Description with emojis
   - Empty description
4. Attempt to create repository

**Expected Results**:
- Descriptions within limits are accepted
- Character count is displayed if there's a limit
- Overly long descriptions show validation errors
- Special characters and emojis are handled correctly
- Empty description is allowed

---

## Test Case 7: README Initialization

**Test Steps**:
1. Create repository with "Add a README file" checked
2. Create another repository without README initialization
3. Compare both repositories

**Expected Results**:
- Repository with README has README.md file in root
- README contains basic repository information
- Repository without README shows setup instructions
- Both repositories are created successfully

---

## Test Case 8: .gitignore Template Selection

**Test Steps**:
1. Navigate to new repository creation page
2. Enter repository details
3. Select different .gitignore templates:
   - Node
   - Python
   - Java
   - None
4. Create repositories and verify contents

**Expected Results**:
- Selected .gitignore template is added to repository
- .gitignore contains appropriate ignore patterns
- File is placed in repository root
- "None" option creates repository without .gitignore

---

## Test Case 9: License Selection

**Test Steps**:
1. Navigate to new repository creation page
2. Test different license options:
   - MIT License
   - Apache License 2.0
   - GNU General Public License v3.0
   - None
3. Create repositories with different licenses

**Expected Results**:
- LICENSE file is created with selected license text
- License information appears in repository details
- License is properly attributed
- No license option doesn't create LICENSE file

---

## Test Case 10: Repository Visibility Settings

**Test Steps**:
1. Create public repository
2. Create private repository
3. Verify visibility settings in both repositories
4. Test access without authentication (for public repo)

**Expected Results**:
- Public repository is accessible to everyone
- Private repository requires authentication
- Repository settings show correct visibility
- Repository list shows correct visibility indicators

---

## Test Case 11: Navigation and UI Elements

**Test Steps**:
1. Navigate to repository creation page
2. Check all UI elements:
   - Form fields and labels
   - Help text and tooltips
   - Button states and interactions
   - Responsive design on different screen sizes
3. Test navigation:
   - Browser back button
   - GitHub navigation links

**Expected Results**:
- All form elements are properly labeled
- Help text provides useful information
- UI is responsive and accessible
- Navigation works correctly

---

## Test Case 12: Repository Creation Performance
**Test Steps**:
1. Create repository and monitor:
   - Form submission time
   - Loading indicators
   - Progress feedback
   - Error handling
2. Test with different connection speeds (if possible)

**Expected Results**:
- Loading indicators appear during creation
- Process completes within reasonable time
- User receives feedback during process
- Errors are handled gracefully

---

## Test Case 13: Post-Creation Repository State

**Test Steps**:
1. Create repository with various settings
2. Immediately check:
   - Repository settings page
   - File structure
   - Commit history
   - Repository statistics
3. Verify all settings match creation choices

**Expected Results**:
- All creation settings are properly applied
- Repository is in expected initial state
- Default branch is created (usually 'main')
- Repository statistics are accurate

---
