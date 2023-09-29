package com.rmaprojects.studentmonitoring.presentation.ui.pages.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Numbers
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.rmaprojects.studentmonitoring.constants.StudentModel
import com.rmaprojects.studentmonitoring.constants.studentList
import com.rmaprojects.studentmonitoring.presentation.ui.components.MyAlertDialog
import com.rmaprojects.studentmonitoring.presentation.ui.pages.home.event.HomeEvent
import com.rmaprojects.studentmonitoring.presentation.ui.state.LogicState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    goToHistoryScreen: () -> Unit,
    backToAuth: () -> Unit
) {

    val scope = rememberCoroutineScope()

    var isDropDownExpanded by remember {
        mutableStateOf(false)
    }

    var isSendViolationDialogShown by remember {
        mutableStateOf(false)
    }

    var isLogOutViolationDialogShown by remember {
        mutableStateOf(false)
    }

    var searchStudentValue by rememberSaveable {
        mutableStateOf("")
    }

    var selectedStudent by rememberSaveable {
        mutableStateOf<StudentModel?>(null)
    }

    var violationDescriptionValue by rememberSaveable {
        mutableStateOf("")
    }

    var pointsValue by rememberSaveable {
        mutableStateOf(0)
    }

    var isTextFieldsError by remember {
        mutableStateOf(false)
    }

    val homeState by viewModel.homeState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    val showSnackbar: (String) -> Unit = { message ->
        scope.launch { snackbarHostState.showSnackbar(message) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Student Monitoring App") },
                actions = {
                    IconButton(
                        onClick = {
                            goToHistoryScreen()
                        }
                    ) {
                        Icon(imageVector = Icons.Default.History, contentDescription = "History")
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(imageVector = Icons.Default.Logout, contentDescription = "Log Out")
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(
                snackbarHostState
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.height(76.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Pelanggaran apa yang terjadi hari ini?",
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(78.dp))
            ExposedDropdownMenuBox(
                modifier = Modifier.fillMaxWidth(),
                expanded = isDropDownExpanded,
                onExpandedChange = { !isDropDownExpanded }
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                        .onFocusChanged {
                            if (!it.isFocused) {
                                if (selectedStudent != null) {
                                    searchStudentValue =
                                        "${selectedStudent?.studentName}, ${selectedStudent?.studentClass}"
                                }
                            }
                        },
                    singleLine = true,
                    value = searchStudentValue,
                    onValueChange = {
                        searchStudentValue = it
                        isDropDownExpanded = true
                    },
                    label = {
                        Text(text = "Nama Siswa")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Person, contentDescription = null)
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropDownExpanded)
                    },
                    colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                    isError = isTextFieldsError,
                    supportingText = {
                        if (isTextFieldsError) Text("Siswa belum di pilih")
                    }
                )

                val filteredList = studentList.filter {
                    it.studentName.contains(searchStudentValue, ignoreCase = true)
                }

                if (filteredList.isNotEmpty()) {
                    DropdownMenu(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .exposedDropdownSize(true),
                        expanded = isDropDownExpanded,
                        onDismissRequest = {
                            isDropDownExpanded = false
                        },
                        properties = PopupProperties(false)
                    ) {
                        filteredList.forEach { studentModel ->
                            DropdownMenuItem(
                                text = {
                                    Text("${studentModel.studentName}, ${studentModel.studentClass}")
                                },
                                onClick = {
                                    isDropDownExpanded = false
                                    selectedStudent = StudentModel(
                                        studentModel.studentName,
                                        studentModel.studentClass
                                    )

                                    searchStudentValue =
                                        "${selectedStudent?.studentName}, ${selectedStudent?.studentClass}"

                                },
                                contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "$pointsValue",
                onValueChange = { newValue ->
                    if (pointsValue in 0..100) {
                        val newNumber = newValue.toInt()
                        pointsValue = newNumber
                    }
                },
                label = {
                    Text(text = "Poin")
                },
                singleLine = true,
                supportingText = {
                    Text("$pointsValue/100")
                },
                trailingIcon = {
                    Icon(imageVector = Icons.Default.Numbers, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 256.dp),
                value = violationDescriptionValue,
                onValueChange = {
                    violationDescriptionValue = it
                },
                placeholder = {
                    Text(text = "Deskripsi pelanggaran")
                },
                isError = isTextFieldsError,
                supportingText = {
                    if (isTextFieldsError) Text(text = "Deskripsi pelanggaran belum diisi")
                }
            )
            Spacer(modifier = Modifier.height(32.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        if (selectedStudent == null || violationDescriptionValue.isEmpty()) {
                            isTextFieldsError = true
                            return@Button
                        }
                        isSendViolationDialogShown = true
                    },
                    enabled = homeState !is LogicState.Loading
                ) {
                    Text(text = "Kirim Laporan")
                }
            }
            when (val state = homeState) {
                is LogicState.Error -> {
                    showSnackbar(state.message)
                    violationDescriptionValue = ""
                    searchStudentValue = ""
                    selectedStudent = null
                    viewModel.onEvent(HomeEvent.DismissStates)
                }

                is LogicState.Loading -> {
                    Spacer(modifier = Modifier.height(32.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is LogicState.Success -> {
                    showSnackbar("Berhasil menyimpan catatan pelanggaran!")
                    violationDescriptionValue = ""
                    searchStudentValue = ""
                    selectedStudent = null
                    viewModel.onEvent(HomeEvent.DismissStates)
                }

                null -> {}
            }
        }
        if (isSendViolationDialogShown) {
            MyAlertDialog(
                title = "Yakin ingin mengirimkan?",
                onDismiss = { isSendViolationDialogShown = false },
                onConfirm = {
                    isSendViolationDialogShown = false
                    viewModel.onEvent(
                        HomeEvent.SubmitViolation(
                            studentName = selectedStudent!!.studentName,
                            studentClass = selectedStudent!!.studentClass,
                            violationDescription = violationDescriptionValue,
                            points = pointsValue
                        )
                    )
                },
                confirmText = "Ya, lanjutkan",
                dismissText = "Tidak jadi",
                message = "Pastikan yang bersangkutan benar-benar melakukan pelanggaran"
            )
        }
        if (isLogOutViolationDialogShown) {
            MyAlertDialog(
                title = "Ingin log out?",
                onDismiss = {
                    isLogOutViolationDialogShown = false
                },
                confirmText = "Ya",
                onConfirm = {
                    isLogOutViolationDialogShown = false
                    viewModel.onEvent(HomeEvent.LogOut)
                    backToAuth()
                },
                dismissText = "Tidak",
                message = "Anda akan diarahkan ke laman login lagi"
            )
        }
    }
}