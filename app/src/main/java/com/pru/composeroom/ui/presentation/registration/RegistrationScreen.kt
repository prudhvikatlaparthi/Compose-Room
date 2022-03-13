package com.pru.composeroom.ui.presentation.registration

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.pru.composeroom.R
import com.pru.composeroom.ui.presentation.custom_components.DatePicker
import com.pru.composeroom.ui.presentation.custom_components.EditText
import com.pru.composeroom.ui.presentation.custom_components.MyButton
import com.pru.composeroom.ui.presentation.custom_components.Spinner
import com.pru.composeroom.utils.ScreenRoute
import kotlinx.coroutines.flow.collectLatest
import java.util.*

@Composable
fun RegistrationScreen(
    navController: NavHostController,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = Unit) {
        viewModel.snackBarMessageState.collectLatest {
            scaffoldState.snackbarHostState.showSnackbar(it)
        }
    }
    LaunchedEffect(key1 = Unit) {
        viewModel.screenNavigation.collectLatest {
            if (it == ScreenRoute.PopBack) {
                navController.popBackStack()
            }
        }
    }
    Scaffold(scaffoldState = scaffoldState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState)
        ) {
            Icon(
                Icons.Default.ArrowBackIosNew,
                contentDescription = Icons.Default.ArrowBack.toString(),
                tint = Color.White,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.please_prv_pat_details),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            ProfilePic(bitmap = viewModel.patientImage, onResult = {
                viewModel.patientImage = it
            })
            Spacer(modifier = Modifier.height(20.dp))
            EditText(
                modifier = Modifier,
                value = viewModel.name,
                onValueChange = {
                    viewModel.name = it
                },
                hint = stringResource(R.string.enter_pat_name),
                onClear = {
                    viewModel.name = ""
                }, keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ))
            Spacer(modifier = Modifier.height(20.dp))
            EditText(
                modifier = Modifier,
                value = viewModel.email ?: "",
                onValueChange = {
                    viewModel.email = it
                },
                hint = stringResource(R.string.enter_pat_email),
                onClear = {
                    viewModel.email = ""
                }, keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Email
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ))
            Spacer(modifier = Modifier.height(20.dp))
            EditText(
                modifier = Modifier,
                value = viewModel.phone,
                onValueChange = {
                    viewModel.phone = it
                },
                hint = stringResource(R.string.enter_pat_phone),
                onClear = {
                    viewModel.phone = ""
                }, keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ))
            Spacer(modifier = Modifier.height(20.dp))
            Spinner(
                modifier = Modifier,
                spinnerLabel = stringResource(R.string.gender),
                spinnerValue = viewModel.selectedGender.name,
                showSpinner = viewModel.openGenderDropdown,
                spinnerData = viewModel.genderList,
                onOpenCloseSpinner = {
                    viewModel.openGenderDropdown = it
                },
                onItemChangeListener = {
                    viewModel.selectedGender = it
                },
                itemContent = {
                    Text(text = it.name, fontSize = 16.sp)
                })
            Spacer(modifier = Modifier.height(20.dp))
            DatePicker(dateLabel = stringResource(R.string.date_of_birth),
                myDateValue = viewModel.selectedDOB,
                disableFutureDates = Date().time,
                onDateChangeListener = {
                    viewModel.selectedDOB = it
                })
            Spacer(modifier = Modifier.height(20.dp))
            Spinner(
                modifier = Modifier,
                spinnerLabel = stringResource(R.string.marital_status),
                spinnerValue = viewModel.selectedMaritalStatus.name,
                showSpinner = viewModel.openMaritalStatusDropdown,
                spinnerData = viewModel.maritalStatusList,
                onOpenCloseSpinner = {
                    viewModel.openMaritalStatusDropdown = it
                },
                onItemChangeListener = {
                    viewModel.selectedMaritalStatus = it
                },
                itemContent = {
                    Text(text = it.name, fontSize = 16.sp)
                })
            Spacer(modifier = Modifier.height(20.dp))
            EditText(
                modifier = Modifier,
                value = viewModel.address,
                onValueChange = {
                    viewModel.address = it
                },
                maxLines = 3,
                singleLine = false,
                hint = stringResource(R.string.address),
                onClear = {
                    viewModel.address = ""
                },
                keyboardOptions = KeyboardOptions(

                ),
                keyboardActions = KeyboardActions(

                )
            )
            Spacer(modifier = Modifier.height(20.dp))
            EditText(
                modifier = Modifier,
                value = viewModel.reasonForReg,
                onValueChange = {
                    viewModel.reasonForReg = it
                },
                maxLines = 3,
                singleLine = false,
                hint = stringResource(R.string.reason_for_reg),
                onClear = {
                    viewModel.reasonForReg = ""
                }
            )
            Spacer(modifier = Modifier.height(20.dp))
            MyButton(
                modifier = Modifier.padding(bottom = 10.dp),
                buttonTitle = stringResource(id = R.string.register), onClick = {
                    viewModel.insertPatient()
                })
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ProfilePic(modifier: Modifier = Modifier, bitmap: Bitmap?, onResult: (image: Bitmap?) -> Unit) {
    val context = LocalContext.current
    val cameraPermissionState = rememberPermissionState(
        android.Manifest.permission.CAMERA
    )
    val cameraCapture = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = onResult
    )
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (cameraPermissionState.status) {
            PermissionStatus.Granted -> {
                if (bitmap == null) {
                    Image(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = null,
                        modifier = modifier
                            .clip(CircleShape)
                            .size(100.dp)
                            .clickable {
                                cameraCapture.launch()
                            },
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = null,
                        modifier = modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .clickable {
                                cameraCapture.launch()
                            },
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop
                    )
                }
            }
            is PermissionStatus.Denied -> {
                if (cameraPermissionState.status.shouldShowRationale) {
                    TextButton(onClick = {
                        try {
                            val intent = Intent()
                            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            val uri: Uri =
                                Uri.fromParts(
                                    context.getString(R.string.package_),
                                    context.packageName,
                                    null
                                )
                            intent.data = uri
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }) {
                        Text(
                            text = "Please enable camera permission from settings in order to use this feature.",
                            color = Color.White
                        )
                    }
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = null,
                        modifier = modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .clickable {
                                cameraPermissionState.launchPermissionRequest()
                            },
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}